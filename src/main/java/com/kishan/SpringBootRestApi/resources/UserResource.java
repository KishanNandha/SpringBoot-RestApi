package com.kishan.SpringBootRestApi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kishan.SpringBootRestApi.dao.UserDao;
import com.kishan.SpringBootRestApi.exception.UserNotFoundException;
import com.kishan.SpringBootRestApi.models.User;

@RestController
public class UserResource {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if(null == user) {
			throw new UserNotFoundException("User with id:"+id+" not found");
		}
		return user;
	}

	@PostMapping("/users")
	public  ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User newUser = userDao.saveUser(user);
		URI location = ServletUriComponentsBuilder   
						.fromCurrentRequest() //gets current request path i.e. localhost:8080/users
						.path("/{id}") //appends {id}
						.buildAndExpand(newUser.getId()) //replaces {id} with actual id
						.toUri(); //builds uri
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void daleteUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if(null == user) {
			throw new UserNotFoundException("User with id:"+id+" not found");
		}
		userDao.deleteOne(user);
	}
}
