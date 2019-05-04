package com.kishan.SpringBootRestApi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kishan.SpringBootRestApi.models.User;

@Component
public class UserDao {
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1, "kishan", new Date()));
		users.add(new User(2, "karan", new Date()));
		users.add(new User(3, "ravi", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}

	public User saveUser(User user) {
		if(null == user.getId()) {
			user.setId(users.size()+1);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		if(id != null) {
			for(User user: users) {
				if(user.getId().equals(id)) {
					return user;
				}
			}
		}
		return null;
	}
}
