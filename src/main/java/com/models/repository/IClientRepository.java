package com.models.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, String> {
	
	/*
	@Query(value="SELECT c.id, c.name, c.last_name, c.email, "
					+ " date_format(c.create_at,'%d-%m-%Y') as create_at,"
					+ " c.photo FROM client c", nativeQuery = true)
	public List<Client> findAll();
	
	@Query(value="SELECT c.id, c.name, c.last_name, c.email, "
			+ " date_format(c.create_at,'%d-%m-%Y') as create_at,"
			+ " c.photo FROM client c WHERE c.id = ?", nativeQuery = true)
    public Optional<Client> findById(String id);
	
	*/
}
