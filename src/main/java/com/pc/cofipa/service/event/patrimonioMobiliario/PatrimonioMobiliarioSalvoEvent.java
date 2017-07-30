package com.pc.cofipa.service.event.patrimonioMobiliario;

import org.springframework.util.StringUtils;

import com.pc.cofipa.model.PatrimonioMobiliario;

public class PatrimonioMobiliarioSalvoEvent {

	private PatrimonioMobiliario  patrimonioMobiliario;

	public PatrimonioMobiliarioSalvoEvent(PatrimonioMobiliario patrimonioMobiliario) {
		super();
		this.patrimonioMobiliario = patrimonioMobiliario;
	}

	public PatrimonioMobiliario getPatrimonioMobiliario() {
		return patrimonioMobiliario;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(patrimonioMobiliario.getFoto());
	}
	
	public boolean isNovaFoto() {
		return patrimonioMobiliario.isNovaFoto();
	}


}