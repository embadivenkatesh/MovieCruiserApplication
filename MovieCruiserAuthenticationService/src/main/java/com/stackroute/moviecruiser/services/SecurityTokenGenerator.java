package com.stackroute.moviecruiser.services;

import java.util.Map;

import com.stackroute.moviecruiser.model.User;


public interface SecurityTokenGenerator {

	Map<String,String> generateToken(User user);
}
