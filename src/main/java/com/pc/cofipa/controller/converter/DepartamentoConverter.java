package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Departamento;

public class DepartamentoConverter implements Converter<String, Departamento>{

	@Override
	public Departamento convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     Departamento departamento = new Departamento();
		     departamento.setCodigo(Long.valueOf(codigo));
		  return departamento;
	  }

	   return null;
 }


}
