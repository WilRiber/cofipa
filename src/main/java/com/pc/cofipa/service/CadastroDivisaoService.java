package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pc.cofipa.model.Divisao;
import com.pc.cofipa.repository.Divisoes;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeDivisaoJaCadastradoException;

@Service
public class CadastroDivisaoService {
	
	
	@Autowired
	private Divisoes divisoes;
	
	@Transactional
	public Divisao salvar(Divisao divisao) {
		Optional<Divisao> divisaoExistente = divisoes.findByNomeAndDepartamento(divisao.getNome(),divisao.getDepartamento());
		if (divisaoExistente.isPresent()) {
			throw new NomeDivisaoJaCadastradoException("Nome da divisao já cadastrado");
		}
		
		return divisoes.save(divisao);
	}
	
	@Transactional
	public void excluir(Divisao divisao){
		try {
		divisoes.delete(divisao);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar divisão");	
		}
	}
	
}