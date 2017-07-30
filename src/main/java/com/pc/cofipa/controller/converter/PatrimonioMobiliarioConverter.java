package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


import com.pc.cofipa.model.PatrimonioMobiliario;

public class PatrimonioMobiliarioConverter implements Converter<String, PatrimonioMobiliario>{

	@Override
	public PatrimonioMobiliario convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     PatrimonioMobiliario patrimonioMobiliario = new PatrimonioMobiliario();
		     patrimonioMobiliario.setCodigo(Long.valueOf(codigo));
		  return patrimonioMobiliario;
	  }

	   return null;
 }


}
