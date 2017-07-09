package com.pc.cofipa.repository.helper.andar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Andar;
import com.pc.cofipa.repository.filter.AndarFilter;



public interface AndaresQueries {
	
	public Page<Andar> filtrar(AndarFilter filtro, Pageable pageable);

}
