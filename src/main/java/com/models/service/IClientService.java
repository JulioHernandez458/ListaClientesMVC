package com.models.service;

import java.util.List;

import com.models.entity.Client;

public interface IClientService {
	
	
    public List<Client> findAll();

    public Client findOne(String id);
    
	public void save(Client client);
	
	public void delete(String id);

}
