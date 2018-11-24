package com.otavio.ebix.bd.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.otavio.ebix.model.Usuario;

@Repository
public class UsuarioRepositorio {

	public static final String INSERIR_USUARIO = "INSERT INTO usuario(nome, email) VALUES(?,?)";

	public static final String LISTAR_USUARIOS = "SELECT * FROM usuario";

	public static final String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE id = ?";

	public static final String EXCLUIR_USUARIO = " DELETE usuario WHERE id = ?";

	public static final String ATUALIZAR_USUARIO = "UPDATE usuario SET nome = ?, email = ? WHERE id = ? ";
	
	public static final String PROXIMA_SEQUENCE = "NEXTVAL('seq_usuario_id')";

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public List<Usuario> listar() {
		return jdbcTemplate.query(UsuarioRepositorio.LISTAR_USUARIOS, new UsuarioRowMapper());
	}

	public void inserir(Usuario usuario) {
		jdbcTemplate.update(UsuarioRepositorio.INSERIR_USUARIO, usuario.getNome(), usuario.getEmail());
	}

	public Usuario buscar(Integer id) {
		return jdbcTemplate.queryForObject(UsuarioRepositorio.BUSCAR_USUARIO, new UsuarioRowMapper(), id);
	}

	public void excluir(Integer id) {
		jdbcTemplate.update(UsuarioRepositorio.EXCLUIR_USUARIO, id);
	}

	public void atualizar(Usuario usuario) {
		jdbcTemplate.update(UsuarioRepositorio.ATUALIZAR_USUARIO, usuario.getNome(), usuario.getEmail(),
				usuario.getId());
	}
}
