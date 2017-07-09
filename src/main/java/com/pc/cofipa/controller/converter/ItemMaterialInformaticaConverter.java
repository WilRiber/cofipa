package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.ItemMaterialInformatica;

public class ItemMaterialInformaticaConverter implements Converter<String, ItemMaterialInformatica> {

	@Override
	public ItemMaterialInformatica convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     ItemMaterialInformatica itemMaterialInformatica = new ItemMaterialInformatica();
		     itemMaterialInformatica.setCodigo(Long.valueOf(codigo));
		  return itemMaterialInformatica;
	  }

	   return null;
 }


}
