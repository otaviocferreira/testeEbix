package com.otavio.ebix.service;

import java.util.List;

import com.otavio.ebix.model.Usuario;

public interface IUsuarioService {
	List<Usuario> getAllUsuarios();

	Usuario getUsuarioById(Integer articleId);

	boolean addUsuario(Usuario article);

	void updateUsuario(Usuario article);

	void deleteUsuario(Usuario article);
}
