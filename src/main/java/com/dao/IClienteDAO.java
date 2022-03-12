package com.dao;

import java.util.List;

import com.model.Cliente;

public interface IClienteDAO {
	
	public List<Cliente> obtenerClientes();
	
	public Cliente guardarCliente(Cliente cliente);
	
	public Cliente obtenerClienteById(int id);
	
	public String eliminarClienteById(int id);

}
