package com.pc.cofipa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.ItemMaterialInformatica;
import com.pc.cofipa.repository.helper.itemMaterialInformatica.ItensMateriaisInformaticaQueries;

@Repository
public interface ItensMateriaisInformatica extends JpaRepository<ItemMaterialInformatica, Long>, ItensMateriaisInformaticaQueries {

	public Optional<ItemMaterialInformatica> findByDescricaoIgnoreCase(String descricao);

}
