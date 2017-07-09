package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Divisao;

public class DivisaoConverter implements Converter<String, Divisao> {

	@Override
	public Divisao convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     Divisao divisao = new Divisao();
		     divisao.setCodigo(Long.valueOf(codigo));
		  return divisao;
	  }

	   return null;
}


}