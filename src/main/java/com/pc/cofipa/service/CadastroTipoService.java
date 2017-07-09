package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Tipo;

import com.pc.cofipa.repository.Tipos;
import com.pc.cofipa.service.exception.DescricaoTipoJaCadastradoException;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroTipoService {

	@Autowired
	private Tipos tipos;
	
	@Transactional
	public Tipo salvar(Tipo tipo ){
		Optional<Tipo> tipoOptional = tipos.findByDescricaoIgnoreCase(tipo.getDescricao());
		if(tipoOptional.isPresent()){
			throw new DescricaoTipoJaCadastradoException("nome do tipo já cadastrada");
		}
		
		return tipos.save(tipo);
	}
	@Transactional
	public void excluir(Tipo tipo){
		try {
		tipos.delete(tipo);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar Tipos");	
		}
	}
}