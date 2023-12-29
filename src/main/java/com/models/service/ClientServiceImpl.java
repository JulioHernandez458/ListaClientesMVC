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
		
		//If new client = CREATE
		if(client.getId() == null) {
			
			try {
				client.setId(UUID.randomUUID().toString());
				log.info(client.toString());
				repo.save(client);
			} catch (Throwable e) {
				throw e;
			}
			
		} else { //if client exists = UPDATE
			log.info("Service ##########");
			log.info(client.toString());
			repo.save(client);
		}
		
	}

	@Override
	@Transactional
	public void delete(String id) {
		repo.deleteById(id);
	}

	

}
