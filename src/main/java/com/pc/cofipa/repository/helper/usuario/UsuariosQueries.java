package com.pc.cofipa.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.pc.cofipa.model.Usuario;

public interface UsuariosQueries {
	
	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario );

}
