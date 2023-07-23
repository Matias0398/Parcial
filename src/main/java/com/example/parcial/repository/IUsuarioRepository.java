package com.example.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parcial.entidad.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

}
