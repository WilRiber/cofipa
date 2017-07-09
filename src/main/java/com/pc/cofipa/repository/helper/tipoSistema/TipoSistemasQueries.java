package com.pc.cofipa.repository.helper.tipoSistema;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.TipoSistema;
import com.pc.cofipa.repository.filter.TipoSistemaFilter;



public interface TipoSistemasQueries {
	
	public Page<TipoSistema> filtrar(TipoSistemaFilter filtro, Pageable pageable);

}
