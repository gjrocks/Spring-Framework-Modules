package com.gj.service;

import java.util.List;

import com.gj.model.User;

public interface UserService {
	public List<User> getAllUser();
	public User getUserById(long id);
	public User saveUser(User todo);
	public void removeUser(User todo);
}
