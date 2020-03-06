package com.stackroute.moviecruiser.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.services.SecurityTokenGenerator;
import com.stackroute.moviecruiser.services.UserService;

@RestController
@EnableWebMvc
@RequestMapping(path = "/api/V1/user")
@CrossOrigin(origins ={"http://localhost:4200","http://localhost:49152" })
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User register successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginuser(@RequestBody User userDetails){
		try {
		String userId=userDetails.getUserId();
		String password=userDetails.getPassword();
		if(userId==null || password==null) {
			throw new Exception("Username or password can not be empty");
		}
		User user=userService.findByUserIdAndPassword(userId, password);
		if(user==null) {
			throw new Exception("User ID does not exist");
		}
		String pwd=user.getPassword();
		if(!password.equals(pwd)) {
			throw new Exception("Invalid credentials,Please check user Id and password ");
		}
		
		Map<String, String> map = securityTokenGenerator.generateToken(user);
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	}
		catch(Exception e) {
		 return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}
}
