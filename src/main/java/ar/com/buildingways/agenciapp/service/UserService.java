package ar.com.buildingways.iplycbackend.service;

import java.util.Collection;

import ar.com.buildingways.iplycbackend.model.User;

public interface UserService {
	
	public Collection<Integer> getLegajos();
	
	public User findUserByUsername(int username);
	
	public void saveUser(User user);
	
	public Collection<User> findAllUsers();
}
