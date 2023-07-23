package com.example.parcial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parcial.entidad.Usuario;
import com.example.parcial.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findbyId(Integer id) {
		
		return usuarioRepository.findById(id);
	}

	

}
