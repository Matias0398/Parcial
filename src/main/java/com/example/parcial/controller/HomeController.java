package com.example.parcial.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.parcial.entidad.DetalleOrden;
import com.example.parcial.entidad.Orden;
import com.example.parcial.entidad.Producto;
import com.example.parcial.entidad.Usuario;
import com.example.parcial.service.DetalleOrdenServiceImpl;
import com.example.parcial.service.OrdenServiceImpl;
import com.example.parcial.service.ProductoService;
import com.example.parcial.service.UsuarioServiceImpl;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private OrdenServiceImpl ordenService;
	
	@Autowired
	private DetalleOrdenServiceImpl detalleOrdenService;
	
	List<DetalleOrden>detalle=new ArrayList<DetalleOrden>();
	
	Orden orden=new Orden();
	
	@GetMapping("")
	public String home(Model model) {  //Muestra la pag. inicial para el user
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}
	
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id,Model model) {
		Producto producto=new Producto();
		Optional<Producto> productoOptional= productoService.get(id);
		producto=productoOptional.get();
		
		model.addAttribute("producto",producto);
		return "usuario/productohome";
	}
	
	@PostMapping("/cart")
	public String AñadirCarrito(@RequestParam Integer id,@RequestParam Integer cantidad,Model model) {
			DetalleOrden detalleOrden=new DetalleOrden();
			Producto producto=new Producto();
			double sumaTotal=0;
			
			Optional<Producto>optionalProducto=productoService.get(id);
			
			producto=optionalProducto.get();
			detalleOrden.setCantidad(cantidad);
			detalleOrden.setPrecio(producto.getPrecio());
			detalleOrden.setNombre(producto.getNombre());
			detalleOrden.setTotal(producto.getPrecio()*cantidad);
			detalleOrden.setProducto(producto);
			
			//Para que el producto no se agregue dos veces
			
			Integer idProducto=producto.getId();
			boolean ingresado=detalle.stream().anyMatch(p ->p.getProducto().getId()==idProducto);
			
			if(!ingresado) {
				detalle.add(detalleOrden);
			}
			
			
			sumaTotal=detalle.stream().mapToDouble(dt->dt.getTotal()).sum();
			
			orden.setTotal(sumaTotal);
			
			model.addAttribute("cart",detalle);
			model.addAttribute("orden",orden);
		
		return "usuario/carrito";
	}
	
	@GetMapping("/delete/cart/{id}")
	public String deleteCarrito(@PathVariable Integer id,Model model) {
		List<DetalleOrden>ordenNueva=new ArrayList<DetalleOrden>();
		for(DetalleOrden detalleOrden: detalle) {
			if(detalleOrden.getProducto().getId()!=id) {
				ordenNueva.add(detalleOrden);
			}
		}
		
		detalle=ordenNueva;
		
		double sumaTotal=0;
		
		sumaTotal=detalle.stream().mapToDouble(dt->dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		
		model.addAttribute("cart",detalle);
		model.addAttribute("orden",orden);
		
		return "usuario/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model) {
		model.addAttribute("cart",detalle);
		model.addAttribute("orden",orden);
		return "usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model) {
		
		Usuario usuario=usuarioService.findbyId(1).get();
		model.addAttribute("cart",detalle);
		model.addAttribute("orden",orden);
		model.addAttribute("usuario",usuario);
		return "usuario/resumenorden";
	}
	
	@GetMapping("/saveOrder")
	public String saveOrder() {
		Date fechaCreacion=new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//Usuario
		Usuario usuario=usuarioService.findbyId(1).get();
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		//Guardar detalle
		for(DetalleOrden dt: detalle) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		//limpiar lista y orden
		
		orden= new Orden();
		detalle.clear();
		return"redirect:/";
	}
}
