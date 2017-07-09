package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Secao;
import com.pc.cofipa.repository.Secoes;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeSecaoJaCadastradoException;

@Service
public class CadastroSecaoService {

	@Autowired
	private Secoes secoes;
	
	@Transactional
	public Secao salvar(Secao secao) {
		Optional<Secao> secaoExixtente = secoes.findByNomeAndDivisao(secao.getNome(), secao.getDivisao());
		if (secaoExixtente.isPresent()) {
			throw new NomeSecaoJaCadastradoException("Nome da seção já cadastrado");
		}
		
		return secoes.save(secao);
	}
	
	@Transactional
	public void excluir(Secao secao){
		try {
		secoes.delete(secao);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar seção");	
		}
	}
	
}