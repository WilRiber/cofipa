package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.Tipo;


public class TipoConverter implements Converter<String, Tipo>{

	@Override
	  public Tipo convert(String codigo) {
		  if(!StringUtils.isEmpty(codigo)) {
		     Tipo tipo = new Tipo();
		     tipo.setCodigo(Long.valueOf(codigo));
		  return tipo;
	  }

	   return null;
}

}

