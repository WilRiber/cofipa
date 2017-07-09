package com.pc.cofipa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.cofipa.model.MaterialInformatica;
import com.pc.cofipa.repository.helper.materialInformatica.MateriaisInformaticaQueries;

@Repository
public interface MateriaisInformatica extends JpaRepository<MaterialInformatica, Long>, MateriaisInformaticaQueries{

	public List<MaterialInformatica> findByItemMaterialInformaticaCodigo(Long codigoItemMaterialInformatica);

	public Optional<MaterialInformatica> findByDescricaoIgnoreCase(String descricao);

}
