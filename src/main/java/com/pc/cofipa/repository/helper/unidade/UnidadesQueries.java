package com.pc.cofipa.repository.helper.unidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Unidade;
import com.pc.cofipa.repository.filter.UnidadeFilter;



public interface UnidadesQueries {
	
	public Page<Unidade> filtrar(UnidadeFilter filtro, Pageable pageable);

}
