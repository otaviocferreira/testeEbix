package com.otavio.ebix.bd.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.otavio.ebix.model.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("ID"));
		usuario.setNome(rs.getString("NOME"));
		usuario.setEmail(rs.getString("EMAIL"));
		return usuario;
	}

}
