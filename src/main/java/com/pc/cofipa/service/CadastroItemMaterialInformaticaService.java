package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pc.cofipa.model.ItemMaterialInformatica;
import com.pc.cofipa.repository.ItensMateriaisInformatica;
import com.pc.cofipa.service.exception.DescricaoItemMaterialInformaticaJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroItemMaterialInformaticaService {

	@Autowired
	private ItensMateriaisInformatica  itensMateriaisInformatica;
	
	@Transactional
	public ItemMaterialInformatica salvar(ItemMaterialInformatica itemMaterialInformatica){
		Optional<ItemMaterialInformatica> ItemMaterialInformaticaOptional = itensMateriaisInformatica.findByDescricaoIgnoreCase(itemMaterialInformatica.getDescricao());
		if(ItemMaterialInformaticaOptional.isPresent()){
			throw new DescricaoItemMaterialInformaticaJaCadastradoException("nome da unidade já cadastrada");
		}
		
		return itensMateriaisInformatica.save(itemMaterialInformatica);
	}

	@Transactional
	public void excluir(ItemMaterialInformatica itemMaterialInformatica){
		try {
		itensMateriaisInformatica.delete(itemMaterialInformatica);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o item do material");	
		}
	}
	

	}