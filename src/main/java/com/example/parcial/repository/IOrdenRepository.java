package com.example.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parcial.entidad.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer>{

}
