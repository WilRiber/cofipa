package com.pc.cofipa.service.event.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pc.cofipa.storage.FotoStorage;

@Component
public class ProdutoListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	
	@EventListener(condition = "#evento.temFoto()")
	public void produtoSalvo(ProdutoSalvoEvent evento){
         fotoStorage.salvar(evento.getProduto().getFoto());
		
	}

}
