package com.pc.cofipa.repository.helper.itemMaterialMobiliario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pc.cofipa.model.ItemMaterialMobiliario;
import com.pc.cofipa.repository.filter.ItemMaterialMobiliarioFilter;

public interface ItensMateriaisMobiliarioQueries {
  
	public Page<ItemMaterialMobiliario> filtrar(ItemMaterialMobiliarioFilter filtro, Pageable pageable);
}
