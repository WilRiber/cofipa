package com.pc.cofipa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.pc.cofipa.model.PatrimonioInformatica;

public class PatrimonioInformaticaConverter implements Converter<String, PatrimonioInformatica>{

	@Override
	public PatrimonioInformatica convert(String codigo) {
		if(!StringUtils.isEmpty(codigo)) {
		     PatrimonioInformatica patrimonioInformatica = new PatrimonioInformatica();
		     patrimonioInformatica.setCodigo(Long.valueOf(codigo));
		  return patrimonioInformatica;
	  }

	   return null;
 }


}