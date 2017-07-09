package com.pc.cofipa.repository.helper.itemMaterialInformatica;

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



import com.pc.cofipa.model.ItemMaterialInformatica;


import com.pc.cofipa.repository.filter.ItemMaterialInformaticaFilter;
import com.pc.cofipa.repository.paginacao.PaginacaoUtil;

public class ItensMateriaisInformaticaImpl implements ItensMateriaisInformaticaQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<ItemMaterialInformatica> filtrar(ItemMaterialInformaticaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ItemMaterialInformatica.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(ItemMaterialInformaticaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ItemMaterialInformatica.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ItemMaterialInformaticaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
				criteria.addOrder(Order.asc("descricao"));
			}
		}
	}

}
