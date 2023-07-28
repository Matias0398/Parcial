package com.example.parcial.service;

import java.util.List;
import java.util.Optional;

import com.example.parcial.entidad.Orden;
import com.example.parcial.entidad.Usuario;

public interface IOrdenService {
	Orden save (Orden orden);
	List<Orden>findAll();
	Optional<Orden> findById(Integer id);
	String generarNumeroOrden();
	List<Orden> findByUsuario (Usuario usuario);
	
}
