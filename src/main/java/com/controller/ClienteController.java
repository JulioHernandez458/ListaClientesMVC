package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.IClienteDAO;
import com.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@RequestMapping("/lista")
	public String getClientes(Model model) {
		
		List<Cliente> clientes = clienteDAO.obtenerClientes();
		
		model.addAttribute("clientes", clientes);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("total", clientes.size());
		return "lista-clientes";
	}
	
	@PostMapping("/agregarCliente")
	public String postCliente(@ModelAttribute("cliente") Cliente nuevoCliente) {
		
		clienteDAO.guardarCliente(nuevoCliente);
		return "redirect:/clientes/lista";
	}
	
	@GetMapping("/editarCliente")
	public String actualizarCliente(@RequestParam("id") int id,Model model) {
		Cliente clienteObtenido = clienteDAO.obtenerClienteById(id);
		if(clienteObtenido != null) {
			model.addAttribute("cliente", clienteObtenido);
			return "editar-clientes";
		}else {
			return null;
		}
		
	}
	
	
	@GetMapping("/eliminarCliente")
	public String deleteCliente(@RequestParam("id") int id, Model model) {
		Cliente clienteObtenido = clienteDAO.obtenerClienteById(id);
		if(clienteObtenido != null) {
			clienteDAO.eliminarClienteById(id);
			return "redirect:/clientes/lista";
		}else {
			return null;
		}
		
	}
	
	

}
