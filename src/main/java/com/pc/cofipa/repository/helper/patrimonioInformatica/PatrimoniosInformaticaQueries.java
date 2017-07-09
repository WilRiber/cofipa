package com.pc.cofipa.repository.helper.patrimonioInformatica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.PatrimonioInformatica;
import com.pc.cofipa.repository.filter.PatrimonioInformaticaFilter;



public interface PatrimoniosInformaticaQueries {
	
	public Page<PatrimonioInformatica> filtrar(PatrimonioInformaticaFilter filtro, Pageable pageable);


}
