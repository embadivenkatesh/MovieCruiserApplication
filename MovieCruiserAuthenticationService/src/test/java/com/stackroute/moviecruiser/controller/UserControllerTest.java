package com.stackroute.moviecruiser.controller;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.services.JwtSecurityTokenGeneratorImpl;
import com.stackroute.moviecruiser.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private transient MockMvc mockMvc;

	@MockBean
	private transient UserServiceImpl service;
	
	
	@MockBean
	private JwtSecurityTokenGeneratorImpl tokenGenerator;	

	private transient static User user;

	@InjectMocks
	private UserController userController;
	
	@Before
	public void setUp() {
		//MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		user = new User("1", "first name", "lastname", "password", new Date());
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		Mockito.when(service.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/api/V1/user/register").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).
				content(convertJsonToString(user)))
				.andExpect(status().isCreated());
		Mockito.verify(service, times(1)).saveUser(Mockito.any(User.class));

	}

	@Test
	public void testLoginUser() throws Exception {
		Mockito.when(service.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		mockMvc.perform(post("/api/V1/user/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(convertJsonToString(user))).andExpect(status().isOk());
		Mockito.verify(service, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());

	}	

	public String convertJsonToString(User user) {
		ObjectMapper obj = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = obj.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;

	}

}