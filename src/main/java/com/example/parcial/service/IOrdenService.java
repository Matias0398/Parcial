package com.example.parcial.service;

import java.util.List;

import com.example.parcial.entidad.Orden;

public interface IOrdenService {
	Orden save (Orden orden);
	List<Orden>findAll();
	String generarNumeroOrden();
}
