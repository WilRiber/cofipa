package com.pc.cofipa.service.event.patrimonioInformatica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pc.cofipa.storage.FotoStorage;

@Component
public class PatrimonioInformaticaListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto()  and #evento.novaFoto")
	public void patrimonioInformaticaSalvo(PatrimonioInformaticaSalvoEvent evento){
		//System.out.println(">>>> tem foto sim: " + evento.getPatrimonioInformatica().getFoto());
		fotoStorage.salvar(evento.getPatrimonioInformatica().getFoto());
	}

}
