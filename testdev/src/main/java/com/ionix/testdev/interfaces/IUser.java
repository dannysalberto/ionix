package com.ionix.testdev.interfaces;

import java.util.List;
import java.util.Optional;

import com.ionix.testdev.dto.UserDTO;
import com.ionix.testdev.model.User;

public interface IUser {
	
	User saveUser(UserDTO user) throws Exception;
	List<User> listUsers();
	Optional<User> getUserByEmail(String email);
	boolean deleteUser(Integer id) throws Exception;

}
