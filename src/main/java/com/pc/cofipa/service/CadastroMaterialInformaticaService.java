package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pc.cofipa.model.MaterialInformatica;

import com.pc.cofipa.repository.MateriaisInformatica;

import com.pc.cofipa.service.exception.DescricaoMaterialInformaticaJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroMaterialInformaticaService {

	@Autowired
	private MateriaisInformatica  materiaisInformatica;
	
	@Transactional
	public MaterialInformatica salvar(MaterialInformatica materialInformatica){
		Optional<MaterialInformatica> MaterialInformaticaOptional = materiaisInformatica.findByDescricaoIgnoreCase(materialInformatica.getDescricao());
		if(MaterialInformaticaOptional.isPresent()){
			throw new DescricaoMaterialInformaticaJaCadastradoException("nome do material já cadastrada");
		}
		
		return materiaisInformatica.save(materialInformatica);
	}

	@Transactional
	public void excluir(MaterialInformatica materialInformatica){
		try {
		materiaisInformatica.delete(materialInformatica);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o material");	
		}
	}
	

	}