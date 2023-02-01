package com.ionix.testdev.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ionix.testdev.common.CommonConstants;
import com.ionix.testdev.dto.UserDTO;
import com.ionix.testdev.model.User;
import com.ionix.testdev.responses.GeneralResponse;
import com.ionix.testdev.responses.UserResponse;
import com.ionix.testdev.services.UserService;
import com.ionix.testdev.utils.ConvertDTO;

@Component
public class UserBusinnes {

	@Autowired
	UserService serviceUser;
	
	@Autowired
	ConvertDTO convertDTO;

	
	@SuppressWarnings("unchecked")
	public ResponseEntity<?> list() {
		
		List<User> users = serviceUser.listUsers(); 
		List<UserResponse> listUser = convertDTO.get(users, UserResponse.class);
		
		if (users.size()>=1)
			return new ResponseEntity<List<UserResponse>>(listUser,HttpStatus.OK);				
		
		return returnNotFound();				
		
	}
	
	public ResponseEntity<?> save(UserDTO user) throws Exception {
		User userToSave = serviceUser.saveUser(user);
		return new ResponseEntity<UserResponse>((UserResponse) convertDTO.create(userToSave, UserResponse.class),HttpStatus.OK);
				
				
	}
	
	public ResponseEntity<?> getByEmail(String email){
		
		Optional<User> userFound = serviceUser.getUserByEmail(email);
		if (userFound.isPresent()) return new ResponseEntity<UserResponse>((UserResponse) convertDTO.create(userFound.get(), UserResponse.class),HttpStatus.OK);
		return returnNotFound();	
								
	}
	
	public ResponseEntity<?> delete(Integer id){
		
	
		if (serviceUser.deleteUser(id)) return new ResponseEntity<GeneralResponse>(responseTrue(),HttpStatus.OK);
		return returnNotFound();
								
	}

	private GeneralResponse responseTrue() {
		GeneralResponse responseTrue = GeneralResponse.builder()
				.code(CommonConstants.SUCCESS_CODE)
				.message(CommonConstants.SUCCESS_MESSAGE).build();
		return responseTrue;
	}
		
	private ResponseEntity<?> returnNotFound() {
		return new ResponseEntity<GeneralResponse>(GeneralResponse.builder()
					.code(CommonConstants.NOTFOUND_CODE)
					.message(CommonConstants.NOTFOUND_MESSAGE).build(),
					HttpStatus.NOT_FOUND);
	}
	
}
