package com.models.service;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.models.entity.Client;
import com.models.repository.IClientRepository;

@Service
public class ClientServiceImpl implements IClientService{
	
	@Autowired
	private IClientRepository repo;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	
	@Override
	public List<Client> findAll(){
		
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(String id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Client client) {
		
		try {
			// If new client = CREATE
			if (client.getId() == null) {
				client.setId(UUID.randomUUID().toString().substring(0, 20));
				repo.save(client);
				log.info("NEW CLIENT SAVED...OK");
			} else { // if client exists = UPDATE
				repo.save(client);
				log.info("CLIENT UPDATED...OK");
			}
		} catch (Throwable e) {
			log.error(e.toString());
			throw e;
		}
		
	}

	@Override
	@Transactional
	public void delete(String id) {
		repo.deleteById(id);
	}

	

}
