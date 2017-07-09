package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Andar;


public class AndarConverter implements Converter<String, Andar>{

	@Override
	  public Andar convert(String codigo) {
		  if(!StringUtils.isEmpty(codigo)) {
		     Andar andar = new Andar();
		     andar.setCodigo(Long.valueOf(codigo));
		  return andar;
	  }

	   return null;
}

}
