package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.MaterialMobiliario;

public class MaterialMobiliarioConverter implements Converter<String, MaterialMobiliario> {

	@Override
	public MaterialMobiliario convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     MaterialMobiliario materialMobiliario = new MaterialMobiliario();
		     materialMobiliario.setCodigo(Long.valueOf(codigo));
		  return materialMobiliario;
	  }

	   return null;
 }


}
