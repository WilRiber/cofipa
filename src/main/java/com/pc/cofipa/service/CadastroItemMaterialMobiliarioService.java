package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.ItemMaterialMobiliario;
import com.pc.cofipa.repository.ItensMateriaisMobiliario;
import com.pc.cofipa.service.exception.DescricaoItemMaterialMobiliarioJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroItemMaterialMobiliarioService {

	@Autowired
	private ItensMateriaisMobiliario  itensMateriaisMobiliario;
	
	@Transactional
	public ItemMaterialMobiliario salvar(ItemMaterialMobiliario itemMaterialMobiliario){
		Optional<ItemMaterialMobiliario> ItemMaterialMobiliarioOptional = itensMateriaisMobiliario.findByDescricaoIgnoreCase(itemMaterialMobiliario.getDescricao());
		if(ItemMaterialMobiliarioOptional.isPresent()){
			throw new DescricaoItemMaterialMobiliarioJaCadastradoException("nome da unidade já cadastrada");
		}
		
		return itensMateriaisMobiliario.save(itemMaterialMobiliario);
	}

	@Transactional
	public void excluir(ItemMaterialMobiliario itemMaterialMobiliario){
		try {
		itensMateriaisMobiliario.delete(itemMaterialMobiliario);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o item do material");	
		}
	}
	

	}