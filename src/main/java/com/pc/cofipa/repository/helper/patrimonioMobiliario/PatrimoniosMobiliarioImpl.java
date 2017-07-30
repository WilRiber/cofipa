package com.pc.cofipa.repository.helper.patrimonioMobiliario;

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

import com.pc.cofipa.model.PatrimonioMobiliario;

import com.pc.cofipa.repository.filter.PatrimonioMobiliarioFilter;
import com.pc.cofipa.repository.paginacao.PaginacaoUtil;

public class PatrimoniosMobiliarioImpl implements PatrimoniosMobiliarioQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<PatrimonioMobiliario> filtrar(PatrimonioMobiliarioFilter filtro, Pageable pageable) {
		Criteria  criteria = manager.unwrap(Session.class).createCriteria(PatrimonioMobiliario.class);
		
		paginacaoUtil.preparar(criteria, pageable);
	    adicionarFiltro(filtro, criteria);
	   
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(PatrimonioMobiliarioFilter filtro) {
	    Criteria criteria = manager.unwrap(Session.class).createCriteria(PatrimonioMobiliario.class);
	    adicionarFiltro(filtro, criteria);
	    criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(PatrimonioMobiliarioFilter filtro, Criteria criteria) {
		if(filtro != null) {
			   if(!StringUtils.isEmpty(filtro.getNumero())){
				   criteria.add(Restrictions.ilike("numero", filtro.getNumero(), MatchMode.ANYWHERE));
			   }
			   
			   if(isUgePresent(filtro)){
				   criteria.add(Restrictions.eq("uge", filtro.getUge()));
			   }
			   
			   if(isItemMaterialMobiliarioPresent(filtro)){
				   criteria.add(Restrictions.eq("itemMaterialMobiliario", filtro.getItemMaterialMobiliario()));
			   }
			   
			   if(isMaterialMobiliarioPresent(filtro)){
				   criteria.add(Restrictions.eq("materialMobiliario", filtro.getMaterialMobiliario()));
			   }
			   
			   if(isDepartamentoPresent(filtro)){
				   criteria.add(Restrictions.eq("departamento", filtro.getDepartamento()));
			   }
			   
			   if(isDivisaoPresent(filtro)){
				   criteria.add(Restrictions.eq("divisao", filtro.getDivisao()));
			   }
			   
			   if(isSecaoPresent(filtro)){
				   criteria.add(Restrictions.eq("secao", filtro.getSecao()));
			   }
			   
			   if(isAndarPresent(filtro)){
				   criteria.add(Restrictions.eq("andar", filtro.getAndar()));
			   }
			      
			   if(isTipoPresent(filtro)){
				   criteria.add(Restrictions.eq("tipo", filtro.getTipo()));
			   }
			   
			   if(filtro.getSerial() != null) {
				   criteria.add(Restrictions.ge("serial", filtro.getSerial()));
			   }
			   
			   if(filtro.getData() != null) {
				   criteria.add(Restrictions.le("data", filtro.getData()));
			   }
			   
			   if(filtro.getSala() != null) {
				   criteria.add(Restrictions.le("sala", filtro.getSala()));
			   }
			   
			   if(filtro.getObs() != null) {
				   criteria.add(Restrictions.le("obs", filtro.getObs()));
			   }
			   
			   if(filtro.getConta() != null) {
				   criteria.add(Restrictions.le("conta", filtro.getConta()));
			   }
			   
			   if(filtro.getValor() != null) {
				   criteria.add(Restrictions.le("valor", filtro.getValor()));
			   }
			   
			   if(filtro.getNl() != null) {
				   criteria.add(Restrictions.le("nl", filtro.getNl()));
			   }
			   
			   if(filtro.getAntigo() != null) {
				   criteria.add(Restrictions.le("antigo", filtro.getAntigo()));
			   }
		   }
	}

	
	private boolean isUgePresent(PatrimonioMobiliarioFilter filtro) {
		
		return filtro.getUge() != null && filtro.getUge().getCodigo() != null;
	}

	private boolean isItemMaterialMobiliarioPresent(PatrimonioMobiliarioFilter filtro) {
	
		return filtro.getItemMaterialMobiliario() != null && filtro.getItemMaterialMobiliario().getCodigo() != null;
	}
	
	private boolean isMaterialMobiliarioPresent(PatrimonioMobiliarioFilter filtro) {
		
		return filtro.getMaterialMobiliario() != null && filtro.getMaterialMobiliario().getCodigo() != null;
	}
	
    private boolean isDepartamentoPresent(PatrimonioMobiliarioFilter filtro) {
		
		return filtro.getDepartamento() != null && filtro.getDepartamento().getCodigo() != null;
	}
    
    private boolean isDivisaoPresent(PatrimonioMobiliarioFilter filtro) {
		
		return filtro.getDivisao() != null && filtro.getDivisao().getCodigo() != null;
	}

    private boolean isSecaoPresent(PatrimonioMobiliarioFilter filtro) {
	
	    return filtro.getSecao() != null && filtro.getSecao().getCodigo() != null;
    }
    
    private boolean isAndarPresent(PatrimonioMobiliarioFilter filtro) {
    	
	    return filtro.getAndar() != null && filtro.getAndar().getCodigo() != null;
    }
      
    private boolean isTipoPresent(PatrimonioMobiliarioFilter filtro) {
    	
	    return filtro.getTipo() != null && filtro.getTipo().getCodigo() != null;
    }

}