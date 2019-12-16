package com.example.oauth2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.oauth2.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
	
	List<AppUser> findByUsername(String username);
}
