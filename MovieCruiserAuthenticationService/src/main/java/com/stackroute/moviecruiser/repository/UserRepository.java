package com.stackroute.moviecruiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.moviecruiser.model.User;

public interface UserRepository extends JpaRepository<User,String>{
	
	User findByUserIdAndPassword(String userId,String password);

}
