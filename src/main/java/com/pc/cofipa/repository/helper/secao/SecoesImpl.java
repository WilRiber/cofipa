package com.pc.cofipa.repository.helper.secao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Secao;

import com.pc.cofipa.repository.filter.SecaoFilter;
import com.pc.cofipa.repository.paginacao.PaginacaoUtil;

public class SecoesImpl implements SecoesQueries{
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Secao> filtrar(SecaoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Secao.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(SecaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Secao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(SecaoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
				criteria.addOrder(Order.asc("nome"));
			}
			if(isDivisaoPresent(filtro)){
				   criteria.add(Restrictions.eq("divisao", filtro.getDivisao()));
			   }
		}
	}
	
private boolean isDivisaoPresent(SecaoFilter filtro) {
		
		return filtro.getDivisao() != null && filtro.getDivisao().getCodigo() != null;
	}

}