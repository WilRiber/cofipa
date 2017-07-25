package com.pc.cofipa.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Produto;
import com.pc.cofipa.repository.Produtos;
import com.pc.cofipa.service.event.produto.ProdutoSalvoEvent;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.storage.FotoStorage;




@Service
public class CadastroProdutoService {
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Produto produto){
		produtos.save(produto);
		
		publisher.publishEvent(new ProdutoSalvoEvent(produto));
	}
	
	@Transactional
	public void excluir(Produto produto) {
		try {
			 
		String foto = produto.getFoto(); 
		produtos.delete(produto);
		produtos.flush();
		fotoStorage.excluir(foto);
		}catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar produto. Já foi usado em alguma saida.");
		}
	}
}
