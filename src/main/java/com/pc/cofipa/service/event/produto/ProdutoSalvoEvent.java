package com.pc.cofipa.service.event.produto;

import org.springframework.util.StringUtils;

import com.pc.cofipa.model.Produto;

public class ProdutoSalvoEvent {
	
	private Produto produto;
	
	public ProdutoSalvoEvent(Produto produto){
		this.produto = produto;
		
	}

	public Produto getProduto() {
		return produto;
	}

	
	public boolean temFoto() {
		return !StringUtils.isEmpty(produto.getFoto());
	}
	
	public boolean isNovaFoto() {
		return produto.isNovaFoto();
	}

}
