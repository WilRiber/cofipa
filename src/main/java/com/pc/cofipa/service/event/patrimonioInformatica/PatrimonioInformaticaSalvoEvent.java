package com.pc.cofipa.service.event.patrimonioInformatica;


import org.springframework.util.StringUtils;

import com.pc.cofipa.model.PatrimonioInformatica;

public class PatrimonioInformaticaSalvoEvent {
	
	private PatrimonioInformatica  patrimonioInformatica;

	public PatrimonioInformaticaSalvoEvent(PatrimonioInformatica patrimonioInformatica) {
		super();
		this.patrimonioInformatica = patrimonioInformatica;
	}

	public PatrimonioInformatica getPatrimonioInformatica() {
		return patrimonioInformatica;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(patrimonioInformatica.getFoto());
	}
	
	public boolean isNovaFoto() {
		return patrimonioInformatica.isNovaFoto();
	}


}
