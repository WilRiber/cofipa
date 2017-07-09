package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Unidade;
import com.pc.cofipa.repository.Unidades;
import com.pc.cofipa.service.exception.DescricaoUnidadeJaCadastradaException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroUnidadeService {
	
	@Autowired
	private Unidades unidades;
	
	@Transactional
	public Unidade salvar(Unidade unidade){
		Optional<Unidade> unidadeOptional = unidades.findByDescricaoIgnoreCase(unidade.getDescricao());
		if(unidadeOptional.isPresent()){
			throw new DescricaoUnidadeJaCadastradaException("nome da unidade já cadastrada");
		}
		
		return unidades.save(unidade);
	}
	@Transactional
	public void excluir(Unidade unidade){
		try {
		unidades.delete(unidade);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar Unidade");	
		}
	}
	

	}
	