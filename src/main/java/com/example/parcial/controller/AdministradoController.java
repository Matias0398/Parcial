package com.example.parcial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.parcial.entidad.Producto;
import com.example.parcial.service.ProductoServiceImpl;

@Controller
@RequestMapping("/administrador")
public class AdministradoController {
	
	@Autowired
	private ProductoServiceImpl productoService;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Producto>producto=productoService.findAll();
		model.addAttribute("producto",producto);
		return "administrador/home";
	}
	
	
}
