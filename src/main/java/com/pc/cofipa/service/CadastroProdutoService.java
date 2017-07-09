package com.pc.cofipa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Produto;
import com.pc.cofipa.repository.Produtos;
import com.pc.cofipa.service.event.produto.ProdutoSalvoEvent;




@Service
public class CadastroProdutoService {
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Produto produto){
		produtos.save(produto);
		
		publisher.publishEvent(new ProdutoSalvoEvent(produto));
	}
	
	
}
