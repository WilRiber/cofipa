package com.pc.cofipa.repository.helper.divisao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Divisao;
import com.pc.cofipa.repository.filter.DivisaoFilter;



public interface DivisoesQueries {
	
	public Page<Divisao> filtrar(DivisaoFilter filtro, Pageable pageable);


}
