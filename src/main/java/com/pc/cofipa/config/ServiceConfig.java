package com.pc.cofipa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



import com.pc.cofipa.service.CadastroProdutoService;
import com.pc.cofipa.storage.FotoStorage;
import com.pc.cofipa.storage.local.FotoStorageLocal;


@ComponentScan(basePackageClasses = CadastroProdutoService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage(){
		return new FotoStorageLocal();
	}

}
