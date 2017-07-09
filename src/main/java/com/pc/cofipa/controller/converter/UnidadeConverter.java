package com.pc.cofipa.controller.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Unidade;

public class UnidadeConverter implements Converter<String, Unidade>{

	@Override
	  public Unidade convert(String codigo) {
		  if(!StringUtils.isEmpty(codigo)) {
		  Unidade unidade = new Unidade();
		  unidade.setCodigo(Long.valueOf(codigo));
		  return unidade;
	  }

	   return null;
}


}
