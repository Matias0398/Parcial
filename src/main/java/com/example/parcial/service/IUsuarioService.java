package com.example.parcial.service;

import java.util.Optional;

import com.example.parcial.entidad.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findbyId(Integer id);

}
