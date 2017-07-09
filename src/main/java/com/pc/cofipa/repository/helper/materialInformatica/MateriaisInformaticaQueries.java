package com.pc.cofipa.repository.helper.materialInformatica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.MaterialInformatica;
import com.pc.cofipa.repository.filter.MaterialInformaticaFilter;



public interface MateriaisInformaticaQueries {
	
	public Page<MaterialInformatica> filtrar(MaterialInformaticaFilter filtro, Pageable pageable);

}
