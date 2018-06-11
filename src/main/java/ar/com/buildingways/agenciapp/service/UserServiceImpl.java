package ar.com.buildingways.agenciapp.service;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.model.Account;
import ar.com.buildingways.agenciapp.model.User;
import ar.com.buildingways.agenciapp.model.UserDetails;
import ar.com.buildingways.agenciapp.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	private RoleService roleService;

//	@Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void loadUsers() {
		Query query = entityManager.createNativeQuery("SELECT * FROM v_Users");
		@SuppressWarnings("unchecked")
		Collection<Object[]> queryResults = query.getResultList();
		Iterator<Object[]> it = queryResults.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			User user = userRepository.findByUsername((int)item[0]);
			if (user != null) {
				// Cuando se implemente el logueo por Active Directory, irá el nombre del usuario que ejecutó la importación del archivo de agencias.
				user.setLastModifiedBy("escobjul");
				user.setLastModifiedDate(new DateTime());
			} else {
				user = new User();
				user.setUsername((Integer)item[0]);
				// Cuando se implemente el logueo por Active Directory, irá el nombre del usuario que ejecutó la importación del archivo de agencias.
				user.setCreatedBy("escobjul");
				user.setCreatedDate(new DateTime());
				// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				user.setRole(roleService.findByRole("USER"));	
			}	
			user.setDeleted(false);
			user.setEnabled(true);
			UserDetails us = new UserDetails(user.getId(), 
					(String) "Calle " + item[2] + " N° " + item[3] + " Localidad: " + item[4] + " C.P.: " + item[5], 
					(String) item[15], (String) item[1], ((BigInteger) item[8]).longValue(), (Integer) item[6], (Short) item[7], 
					user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(), user.isDeleted(), user.isEnabled());
			us.setUser(user);
			
			Account a = new Account(user.getId(),
					(Integer) item[10], (Integer) item[9], (String) item[1], (char) item[11], 
					Integer.parseInt((String)item[12]), ((BigDecimal) item[13]).doubleValue(), (String) item[14], 
					user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(), user.isDeleted(), user.isEnabled());
			a.setUser(user);
			user.setUserDetails(us);
			user.setAccount(a);
			userRepository.save(user);
		}
	}
	
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