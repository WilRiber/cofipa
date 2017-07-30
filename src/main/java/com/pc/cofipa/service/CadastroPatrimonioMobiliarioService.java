package com.pc.cofipa.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pc.cofipa.model.PatrimonioMobiliario;

import com.pc.cofipa.repository.PatrimoniosMobiliario;

import com.pc.cofipa.service.event.patrimonioMobiliario.PatrimonioMobiliarioSalvoEvent;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;
import com.pc.cofipa.storage.FotoStorage;

@Service
public class CadastroPatrimonioMobiliarioService {

	@Autowired
	private PatrimoniosMobiliario patrimoniosMobiliario;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	
	@Transactional
	public void salvar(PatrimonioMobiliario patrimonioMobiliario) {
		
		publisher.publishEvent(new PatrimonioMobiliarioSalvoEvent(patrimonioMobiliario));
		
		patrimoniosMobiliario.save(patrimonioMobiliario);
	}

    @Transactional
	public void excluir(PatrimonioMobiliario patrimonioMobiliario) {
		try {
			 
			String foto = patrimonioMobiliario.getFoto(); 
			patrimoniosMobiliario.delete(patrimonioMobiliario);
			patrimoniosMobiliario.flush();
			fotoStorage.excluir(foto);
			}catch (PersistenceException e) {
				throw new ImpossivelExcluirEntidadeException("Impossivel apagar mobiliario. Já foi usado em alguma saida.");
			}
		}
	}