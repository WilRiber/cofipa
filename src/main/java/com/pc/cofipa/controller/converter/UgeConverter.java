package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Uge;

public class UgeConverter implements Converter<String, Uge> {

	@Override
	  public Uge convert(String codigo) {
		  if(!StringUtils.isEmpty(codigo)) {
		     Uge uge = new Uge();
		     uge.setCodigo(Long.valueOf(codigo));
		  return uge;
	  }

	   return null;
}

}
