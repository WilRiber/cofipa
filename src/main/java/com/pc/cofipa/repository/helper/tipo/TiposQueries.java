package com.pc.cofipa.repository.helper.tipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Tipo;
import com.pc.cofipa.repository.filter.TipoFilter;



public interface TiposQueries {
	
	public Page<Tipo> filtrar(TipoFilter filtro, Pageable pageable);

}
