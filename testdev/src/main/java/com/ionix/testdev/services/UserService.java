package com.ionix.testdev.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ionix.testdev.dao.UserRepository;
import com.ionix.testdev.dto.UserDTO;
import com.ionix.testdev.interfaces.IUser;
import com.ionix.testdev.model.User;

@Service
public class UserService implements IUser{
	
    Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository repo;
	
	@Override
	public User saveUser(UserDTO user) throws Exception {
		// TODO Auto-generated method stub
		User userToSave = User.builder()
				.email(user.getEmail())
				.name(user.getName())
				.phone(user.getPhone())
				.build();
		try {
			userToSave = repo.save(userToSave);
			return userToSave;
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getCause().getCause().getMessage());
			throw new Exception(e.getCause().getCause().getMessage());
		}
		
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return repo.findAll(); 

	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
		
	}

	@Override
	public boolean deleteUser(Integer id) {
		// TODO Auto-generated method stub
		try {
			repo.deleteById(id);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return false;
		}
	}

}
