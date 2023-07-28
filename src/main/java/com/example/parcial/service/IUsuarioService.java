package com.example.parcial.service;

import java.util.List;
import java.util.Optional;

import com.example.parcial.entidad.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findbyId(Integer id);
	Usuario save(Usuario usuario);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> findAll();
}
