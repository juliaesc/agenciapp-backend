package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import ar.com.buildingways.agenciapp.model.User;

public interface UserService {
	
	public void loadUsers();
	
	public Collection<Integer> getLegajos();
	
	public User findUserByUsername(int username);
	
	public void saveUser(User user);
	
	public Collection<User> findAllUsers();
}
