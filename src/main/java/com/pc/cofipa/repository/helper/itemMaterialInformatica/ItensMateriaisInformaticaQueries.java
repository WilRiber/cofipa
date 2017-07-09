package com.pc.cofipa.repository.helper.itemMaterialInformatica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.pc.cofipa.model.ItemMaterialInformatica;

import com.pc.cofipa.repository.filter.ItemMaterialInformaticaFilter;

public interface ItensMateriaisInformaticaQueries {
	
	public Page<ItemMaterialInformatica> filtrar(ItemMaterialInformaticaFilter filtro, Pageable pageable);

}
