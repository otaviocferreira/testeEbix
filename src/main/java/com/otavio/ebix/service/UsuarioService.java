package com.otavio.ebix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otavio.ebix.bd.repositorio.UsuarioRepositorio;
import com.otavio.ebix.model.Usuario;

@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public Usuario getUsuarioById(Integer id) {
		return usuarioRepositorio.buscar(id);
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> list = new ArrayList<>();
		usuarioRepositorio.listar().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public boolean addUsuario(Usuario usuario) {
		try {			
			usuarioRepositorio.inserir(usuario);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		usuarioRepositorio.atualizar(usuario);
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		usuarioRepositorio.excluir(usuario.getId());
	}
}