package com.pc.cofipa.repository.helper.materialMobiliario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.MaterialMobiliario;
import com.pc.cofipa.repository.filter.MaterialMobiliarioFilter;

public interface MateriaisMobiliarioQueries {
	
	public Page<MaterialMobiliario> filtrar(MaterialMobiliarioFilter filtro, Pageable pageable);

}
