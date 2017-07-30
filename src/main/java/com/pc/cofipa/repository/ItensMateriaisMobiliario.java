package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.ItemMaterialMobiliario;
import com.pc.cofipa.repository.helper.itemMaterialMobiliario.ItensMateriaisMobiliarioQueries;

@Repository
public interface ItensMateriaisMobiliario extends JpaRepository<ItemMaterialMobiliario, Long>, ItensMateriaisMobiliarioQueries{
	
	public Optional<ItemMaterialMobiliario> findByDescricaoIgnoreCase(String descricao);

}
