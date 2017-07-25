package com.pc.cofipa.repository.helper.patrimonioInformatica;

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

import com.pc.cofipa.model.PatrimonioInformatica;

import com.pc.cofipa.repository.filter.PatrimonioInformaticaFilter;

import com.pc.cofipa.repository.paginacao.PaginacaoUtil;

public class PatrimoniosInformaticaImpl implements PatrimoniosInformaticaQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<PatrimonioInformatica> filtrar(PatrimonioInformaticaFilter filtro, Pageable pageable) {
		Criteria  criteria = manager.unwrap(Session.class).createCriteria(PatrimonioInformatica.class);
		
		paginacaoUtil.preparar(criteria, pageable);
	    adicionarFiltro(filtro, criteria);
	   
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(PatrimonioInformaticaFilter filtro) {
	    Criteria criteria = manager.unwrap(Session.class).createCriteria(PatrimonioInformatica.class);
	    adicionarFiltro(filtro, criteria);
	    criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}


	private void adicionarFiltro(PatrimonioInformaticaFilter filtro, Criteria criteria) {
		if(filtro != null) {
			   if(!StringUtils.isEmpty(filtro.getNumero())){
				   criteria.add(Restrictions.ilike("numero", filtro.getNumero(), MatchMode.ANYWHERE));
			   }
			   
			   if(isUgePresent(filtro)){
				   criteria.add(Restrictions.eq("uge", filtro.getUge()));
			   }
			   
			   if(isItemMaterialInformaticaPresent(filtro)){
				   criteria.add(Restrictions.eq("itemMaterialInformatica", filtro.getItemMaterialInformatica()));
			   }
			   
			   if(isMaterialInformaticaPresent(filtro)){
				   criteria.add(Restrictions.eq("materialInformatica", filtro.getMaterialInformatica()));
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
			   
			   if(isTipoSistemaPresent(filtro)){
				   criteria.add(Restrictions.eq("tipoSistema", filtro.getTipoSistema()));
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
			   
			   if(filtro.getMac() != null) {
				   criteria.add(Restrictions.le("mac", filtro.getMac()));
			   }
			   
			   if(filtro.getIp() != null) {
				   criteria.add(Restrictions.le("ip", filtro.getIp()));
			   }
			   
			   if(filtro.getRede() != null) {
				   criteria.add(Restrictions.le("rede", filtro.getRede()));
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

	
	private boolean isUgePresent(PatrimonioInformaticaFilter filtro) {
		
		return filtro.getUge() != null && filtro.getUge().getCodigo() != null;
	}

	private boolean isItemMaterialInformaticaPresent(PatrimonioInformaticaFilter filtro) {
	
		return filtro.getItemMaterialInformatica() != null && filtro.getItemMaterialInformatica().getCodigo() != null;
	}
	
	private boolean isMaterialInformaticaPresent(PatrimonioInformaticaFilter filtro) {
		
		return filtro.getMaterialInformatica() != null && filtro.getMaterialInformatica().getCodigo() != null;
	}
	
    private boolean isDepartamentoPresent(PatrimonioInformaticaFilter filtro) {
		
		return filtro.getDepartamento() != null && filtro.getDepartamento().getCodigo() != null;
	}
    
    private boolean isDivisaoPresent(PatrimonioInformaticaFilter filtro) {
		
		return filtro.getDivisao() != null && filtro.getDivisao().getCodigo() != null;
	}

    private boolean isSecaoPresent(PatrimonioInformaticaFilter filtro) {
	
	    return filtro.getSecao() != null && filtro.getSecao().getCodigo() != null;
    }
    
    private boolean isAndarPresent(PatrimonioInformaticaFilter filtro) {
    	
	    return filtro.getAndar() != null && filtro.getAndar().getCodigo() != null;
    }
    
    private boolean isTipoSistemaPresent(PatrimonioInformaticaFilter filtro) {
    	
	    return filtro.getTipoSistema() != null && filtro.getTipoSistema().getCodigo() != null;
    }
     
    private boolean isTipoPresent(PatrimonioInformaticaFilter filtro) {
    	
	    return filtro.getTipo() != null && filtro.getTipo().getCodigo() != null;
    }

}
