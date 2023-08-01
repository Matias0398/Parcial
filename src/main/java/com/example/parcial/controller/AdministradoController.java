package com.example.parcial.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.parcial.entidad.Orden;
import com.example.parcial.entidad.Producto;
import com.example.parcial.service.IOrdenService;
import com.example.parcial.service.IUsuarioService;
import com.example.parcial.service.ProductoService;


@Controller
@RequestMapping("/administrador")
public class AdministradoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	
	private Logger logger = LoggerFactory.getLogger(AdministradoController.class);
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Producto>producto=productoService.findAll();
		model.addAttribute("producto",productoService.findAll());
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", ordenService.findAll());
		return "administrador/ordenes";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logger.info("Id de la orden {}",id);
		Orden orden= ordenService.findById(id).get();
		
		model.addAttribute("detalles", orden.getDetalle());
		
		return "administrador/detalleorden";
	}
	

	
	
	
}
