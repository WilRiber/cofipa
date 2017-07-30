package com.pc.cofipa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.pc.cofipa.model.MaterialMobiliario;
import com.pc.cofipa.repository.helper.materialMobiliario.MateriaisMobiliarioQueries;

@Repository
public interface MateriaisMobiliario extends JpaRepository<MaterialMobiliario, Long>, MateriaisMobiliarioQueries{

	public Optional<MaterialMobiliario> findByDescricaoIgnoreCase(String descricao);

	public List<MaterialMobiliario> findByItemMaterialMobiliarioCodigo(Long codigoItemMaterialMobiliario);

}
