package com.example.parcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parcial.entidad.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
