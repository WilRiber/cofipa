package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.TipoSistema;
import com.pc.cofipa.repository.TipoSistemas;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

import com.pc.cofipa.service.exception.NomeSistemaJaCadastradoException;

@Service
public class CadastroTipoSistemaService {
	
	@Autowired
	private TipoSistemas tipoSistemas;
	
	@Transactional
	public TipoSistema salvar(TipoSistema tipoSistema) {
		Optional<TipoSistema> tipoSistemaOptional = tipoSistemas.findByNomeIgnoreCase(tipoSistema.getNome());
		if (tipoSistemaOptional.isPresent()) {
			throw new NomeSistemaJaCadastradoException("Nome do tipo de sistema já cadastrado");
		}
		
		return tipoSistemas.saveAndFlush(tipoSistema);
	}
	
	@Transactional
	public void excluir(TipoSistema tipoSistema){
		try {
		tipoSistemas.delete(tipoSistema);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o tipo sistema");	
		}
	}
	
}


