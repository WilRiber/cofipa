package com.pc.cofipa.repository.helper.uge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.Uge;
import com.pc.cofipa.repository.filter.UgeFilter;



public interface UgesQueries {
	
	public Page<Uge> filtrar(UgeFilter filtro, Pageable pageable);

}
