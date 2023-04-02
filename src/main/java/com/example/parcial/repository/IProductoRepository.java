package com.example.parcial.repository;

import com.example.parcial.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
