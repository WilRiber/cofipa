package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Andar;

import com.pc.cofipa.repository.Andares;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeAndarJaCadastradoException;

@Service
public class CadastroAndarService {
	
	@Autowired
	private Andares andares;
	
	@Transactional
	public Andar salvar(Andar andar){
		Optional<Andar> andarOptional = andares.findByNomeIgnoreCase(andar.getNome());
		if(andarOptional.isPresent()){
			throw new NomeAndarJaCadastradoException("nome do andar já cadastrado");
		}
		
		return andares.save(andar);
	}

	@Transactional
	public void excluir(Andar andar){
		try {
		andares.delete(andar);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar andar!");	
		}
	}
	
}
