package com.pc.cofipa.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.cofipa.model.PatrimonioInformatica;

import com.pc.cofipa.repository.PatrimoniosInformatica;
import com.pc.cofipa.service.event.patrimonioInformatica.PatrimonioInformaticaSalvoEvent;
import com.pc.cofipa.service.exception.ImpossivelExcluirEntidadeException;

import com.pc.cofipa.storage.FotoStorage;

@Service
public class CadastroPatrimonioInformaticaService {

	@Autowired
	private PatrimoniosInformatica patrimoniosInformatica;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	
	@Transactional
	public void salvar(PatrimonioInformatica patrimonioInformatica) {
		
		publisher.publishEvent(new PatrimonioInformaticaSalvoEvent(patrimonioInformatica));
		
		patrimoniosInformatica.save(patrimonioInformatica);
	}

    @Transactional
	public void excluir(PatrimonioInformatica patrimonioInformatica) {
		try {
			 
			String foto = patrimonioInformatica.getFoto(); 
			patrimoniosInformatica.delete(patrimonioInformatica);
			patrimoniosInformatica.flush();
			fotoStorage.excluir(foto);
			}catch (PersistenceException e) {
				throw new ImpossivelExcluirEntidadeException("Impossivel apagar produto. Já foi usado em alguma saida.");
			}
		}
	}
