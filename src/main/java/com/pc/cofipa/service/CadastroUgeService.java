package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Uge;
import com.pc.cofipa.repository.Uges;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

import com.pc.cofipa.service.exception.NomeUgeJaCadastradoException;

@Service
public class CadastroUgeService {
	
	@Autowired
	private Uges uges;
	
	@Transactional
	public Uge salvar(Uge uge){
		Optional<Uge> ugeOptional = uges.findByNomeIgnoreCase(uge.getNome());
		if(ugeOptional.isPresent()){
			throw new NomeUgeJaCadastradoException("nome da uge já cadastrado");
		}
		
		return uges.save(uge);
	}

	@Transactional
	public void excluir(Uge uge){
		try {
		uges.delete(uge);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar uge!");	
		}
	}
	
}


