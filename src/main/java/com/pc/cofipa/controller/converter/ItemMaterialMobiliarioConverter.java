package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.ItemMaterialMobiliario;

public class ItemMaterialMobiliarioConverter implements Converter<String, ItemMaterialMobiliario>{

	@Override
	public ItemMaterialMobiliario convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     ItemMaterialMobiliario itemMaterialMobiliario = new ItemMaterialMobiliario();
		     itemMaterialMobiliario.setCodigo(Long.valueOf(codigo));
		  return itemMaterialMobiliario;
	  }

	   return null;
 }


}