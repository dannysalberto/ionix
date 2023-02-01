package com.ionix.testdev;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ionix.testdev.dto.UserDTO;
import com.ionix.testdev.model.User;
import com.ionix.testdev.services.CipherService;
import com.ionix.testdev.services.UserService;
import com.ionix.testdev.utils.ConvertDTO;


@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
		
	@MockBean
	ConvertDTO convertDTO;

	@Test
	void saveUser() throws Exception {
		UserDTO user = new UserDTO();
		user.setEmail("dannysmuria11@hotmail.com");
		user.setName("Dannys Muria");
		user.setPhone("04124579238");
		
		ObjectMapper mapper = new ObjectMapper();
					
		mockMvc.perform(post("/users/create").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION,"Basic " + Base64Utils.encodeToString("admin:ionixdev".getBytes()))
				.content(mapper.writeValueAsString(user)))
			.andExpect(status().isOk());
		
	}
	
	@Test
	void saveWithoutCredentials() throws Exception {
		UserDTO user = new UserDTO();
		user.setEmail("dannysmuria11@hotmail.com");
		user.setName("Dannys Muria");
		user.setPhone("04124579238");
		
		ObjectMapper mapper = new ObjectMapper();
					
		mockMvc.perform(post("/users/create").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
			.andExpect(status().isUnauthorized());
		
	}
	
	@Test
	void listUser() throws Exception {
	
		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Dannys Muria","dannysmuria@hotmail.com","04124579238", new Date()));
		when(userService.listUsers())
	    .thenReturn(userList);
	 
					
		mockMvc.perform(get("/users/list").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		
	}
	
	@Test
	void validationRUT() throws Exception {
	
		String rut = "1-9";
		mockMvc.perform(post("/rutvalidation/valid/"+rut).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,"Basic " + Base64Utils.encodeToString("admin:ionixdev".getBytes())))
			.andExpect(status().isOk());
		
	}

	
	
}
