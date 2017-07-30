package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.MaterialMobiliario;
import com.pc.cofipa.repository.MateriaisMobiliario;
import com.pc.cofipa.service.exception.DescricaoMaterialMobiliarioJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroMaterialMobiliarioService {

	@Autowired
	private MateriaisMobiliario  materiaisMobiliario;
	
	@Transactional
	public MaterialMobiliario salvar(MaterialMobiliario materialMobiliario){
		Optional<MaterialMobiliario> MaterialMobiliarioOptional = materiaisMobiliario.findByDescricaoIgnoreCase(materialMobiliario.getDescricao());
		if(MaterialMobiliarioOptional.isPresent()){
			throw new DescricaoMaterialMobiliarioJaCadastradoException("nome do material já cadastrada");
		}
		
		return materiaisMobiliario.save(materialMobiliario);
	}

	@Transactional
	public void excluir(MaterialMobiliario materialMobiliario){
		try {
		materiaisMobiliario.delete(materialMobiliario);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o material");	
		}
	}
	

	}