package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Cliente;

@Repository
public class ClienteDAOImpl implements IClienteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Cliente> obtenerClientes() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Cliente> query = session.createQuery("from Cliente", Cliente.class);
		
		List<Cliente> clientes = query.getResultList();	
		
		return clientes;
	}

	@Override
	@Transactional
	public Cliente guardarCliente(Cliente cliente) {
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(cliente);
			return cliente;
					
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Cliente obtenerClienteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Cliente cliente = session.get(Cliente.class, id);
		return cliente;
	}

	@Override
	@Transactional
	public String eliminarClienteById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Cliente where " + 
		                       "id =: clienteID");
		query.setParameter("clienteID", id);
		query.executeUpdate();
		return "cliente eliminado";
	}

}
