package ar.com.buildingways.agenciapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.model.User;
import ar.com.buildingways.agenciapp.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
		
	@Override
	public Collection<Integer> getLegajos() {
		Collection<Integer>legajos = new ArrayList<Integer>();
		Iterator<User> usersList = userRepository.findAll().iterator();
		while(usersList.hasNext()) {
			usersList.next();
			legajos.add(usersList.next().getUsername());
		}
		return legajos;
	}
	
	@Override
	public User findUserByUsername(int username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//        user.setRoles(user.getRoles());
//		userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
}