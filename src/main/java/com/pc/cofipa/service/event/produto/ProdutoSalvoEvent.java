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

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(produto.getFoto());
	}

}
