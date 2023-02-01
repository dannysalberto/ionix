package com.ionix.testdev.controllers;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ionix.testdev.business.UserBusinnes;
import com.ionix.testdev.dto.UserDTO;
import com.ionix.testdev.services.CipherService;
import com.ionix.testdev.services.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@Tag(name= "users", description =  "users" )
@SecurityRequirement(name = "basicAuth")
@OpenAPIDefinition(info = @Info(title = "Developer Test, IONIX", version = "v1"))
public class UserController {

	  
	@Autowired
	UserBusinnes userBusinnes;
	
	@Autowired
	UserService service;
	
	@Autowired
	CipherService cipher;
	
	@PostConstruct
	public void test() throws Exception {
		UserDTO user = new UserDTO("dannys","dannysalberto@gmail.com","pruebas");

		System.out.println(cipher.CipherKey("1-9"));
		System.out.println(service.listUsers());;
		
	}
	
	@Operation(summary = "Get users list " )
	@GetMapping(value="list" , produces = {"application/json"} )
	public ResponseEntity<?> list(){
        return userBusinnes.list();
    }
	
	@Operation(summary = "Create user ")
	@PostMapping(value="create" , produces = {"application/json"} )
    public ResponseEntity<?> save(@Valid @RequestBody  UserDTO user) throws Exception  {
        return userBusinnes.save(user);
    }
	
	@Operation(summary = "Delete user by id")
	@DeleteMapping(value="delete/{id}" , produces = {"application/json"} )
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception  {
        return userBusinnes.delete(id);
    }
	
	
	@Operation(summary = "Get an user by email")
	@GetMapping(value="getbyemail/{email}" , produces = {"application/json"} )
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) throws Exception  {
        return userBusinnes.getByEmail(email);
    }
	

}
