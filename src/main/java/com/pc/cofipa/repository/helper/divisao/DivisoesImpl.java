package com.pc.cofipa.repository.helper.divisao;

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


import com.pc.cofipa.model.Divisao;

import com.pc.cofipa.repository.filter.DivisaoFilter;

import com.pc.cofipa.repository.paginacao.PaginacaoUtil;

public class DivisoesImpl implements DivisoesQueries {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Divisao> filtrar(DivisaoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Divisao.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(DivisaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Divisao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(DivisaoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
				criteria.addOrder(Order.asc("nome"));
			}
			if(isDepartamentoPresent(filtro)){
				   criteria.add(Restrictions.eq("departamento", filtro.getDepartamento()));
			   }
		}
	}
	
private boolean isDepartamentoPresent(DivisaoFilter filtro) {
		
		return filtro.getDepartamento() != null && filtro.getDepartamento().getCodigo() != null;
	}

}