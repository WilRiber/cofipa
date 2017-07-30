package com.pc.cofipa.repository.helper.patrimonioMobiliario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.PatrimonioMobiliario;
import com.pc.cofipa.repository.filter.PatrimonioMobiliarioFilter;

public interface PatrimoniosMobiliarioQueries {
	
	public Page<PatrimonioMobiliario> filtrar(PatrimonioMobiliarioFilter filtro, Pageable pageable);

}
