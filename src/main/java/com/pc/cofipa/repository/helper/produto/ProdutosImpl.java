package com.pc.cofipa.repository.helper.produto;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Produto;
import com.pc.cofipa.repository.filter.ProdutoFilter;
import com.pc.cofipa.repository.paginacao.PaginacaoUtil;

public class ProdutosImpl implements ProdutosQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
		Criteria  criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		
		paginacaoUtil.preparar(criteria, pageable);
	    adicionarFiltro(filtro, criteria);
	   
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(ProdutoFilter filtro) {
	    Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
	    adicionarFiltro(filtro, criteria);
	    criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			   if(!StringUtils.isEmpty(filtro.getDescricao())){
				   criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			   }
			   
			   if(isUnidadePresent(filtro)){
				   criteria.add(Restrictions.eq("unidade", filtro.getUnidade()));
			   }
			   
			   if(isFornecedorPresent(filtro)){
				   criteria.add(Restrictions.eq("fornecedor", filtro.getFornecedor()));
			   }
			   
			   if(filtro.getValorDe() != null) {
				   criteria.add(Restrictions.ge("valortotal", filtro.getValorDe()));
			   }
			   
			   if(filtro.getValorAte() != null) {
				   criteria.add(Restrictions.le("valortotal", filtro.getValorAte()));
			   }
		   }
	}

	
	private boolean isFornecedorPresent(ProdutoFilter filtro) {
		
		return filtro.getFornecedor() != null && filtro.getFornecedor().getCodigo() != null;
	}

	private boolean isUnidadePresent(ProdutoFilter filtro) {
	
		return filtro.getUnidade() != null && filtro.getUnidade().getCodigo() != null;
	}

}
