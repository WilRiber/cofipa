package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.TipoSistema;

public class TipoSistemaConverter implements Converter<String, TipoSistema> {

	@Override
	  public TipoSistema convert(String codigo) {
		  if(!StringUtils.isEmpty(codigo)) {
		     TipoSistema tipoSistema = new TipoSistema();
		     tipoSistema.setCodigo(Long.valueOf(codigo));
		  return tipoSistema;
	  }

	   return null;
}

}

