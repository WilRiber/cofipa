package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Secao;

public class SecaoConverter implements Converter<String , Secao>{

	@Override
	public Secao convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     Secao secao = new Secao();
		     secao.setCodigo(Long.valueOf(codigo));
		  return secao;
	  }

	   return null;
}


}
