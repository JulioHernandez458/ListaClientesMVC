package com.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.models.entity.User;


public interface IUserRepository extends CrudRepository<User, Long>{

	public User findByUsername(String username);
}
