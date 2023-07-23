package com.example.parcial.service;

import org.springframework.stereotype.Service;

import com.example.parcial.entidad.DetalleOrden;
import com.example.parcial.repository.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{

	private IDetalleOrdenRepository detalleOrdenRepository;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}
	

}
