package com.pc.cofipa.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.repository.Departamentos;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.service.exception.NomeDepartamentoJaCadastradoException;



@Service
public class CadastroDepartamentoService {
	
	
	@Autowired
	private Departamentos departamentos;
	
	@Transactional
	public Departamento salvar(Departamento departamento) {
		Optional<Departamento> departamentoOptional = departamentos.findByNomeIgnoreCase(departamento.getNome());
		if (departamentoOptional.isPresent()) {
			throw new NomeDepartamentoJaCadastradoException("Nome do departamento já cadastrado");
		}
		
		return departamentos.saveAndFlush(departamento);
	}
	
	@Transactional
	public void excluir(Departamento departamento){
		try {
		departamentos.delete(departamento);
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar departamento");	
		}
	}
	

	}
	

