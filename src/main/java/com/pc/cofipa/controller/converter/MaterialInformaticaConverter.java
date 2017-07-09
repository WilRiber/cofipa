package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.MaterialInformatica;

public class MaterialInformaticaConverter implements Converter<String, MaterialInformatica>{

	@Override
	public MaterialInformatica convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     MaterialInformatica materialInformatica = new MaterialInformatica();
		     materialInformatica.setCodigo(Long.valueOf(codigo));
		  return materialInformatica;
	  }

	   return null;
 }


}