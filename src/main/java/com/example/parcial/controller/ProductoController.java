package com.example.parcial.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.parcial.entidad.Producto;
import com.example.parcial.entidad.Usuario;
import com.example.parcial.service.ProductoServiceImpl;
import com.example.parcial.service.UploadFileService;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImpl productoService;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model ) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
		
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
		
		//imagenes
		if(producto.getId()==null) {
			String nombreImagen=upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}else {
		}
		productoService.save(producto);
		return "redirect:/productos";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model ) {
		Producto producto=new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto= optionalProducto.get();
		model.addAttribute("producto", producto);
		return "productos/edit";
	}
	@PostMapping("/update")
	public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
		
		Producto p=new Producto();
		p=productoService.get(producto.getId()).get();
		
		if(file.isEmpty()) { //editar el producto pero no la imagen
			
			producto.setImagen(p.getImagen());
		}else {			
			//elimina cuando la imagen no sea por defecto
			if(!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen=upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Producto p=new Producto();
		p=productoService.get(id).get();
		
		//elimina cuando la imagen no sea por defecto
		if(!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		productoService.delete(id);
		return "redirect:/productos";
	}
	
}
