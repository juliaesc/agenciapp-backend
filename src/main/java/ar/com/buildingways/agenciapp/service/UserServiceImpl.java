package ar.com.buildingways.agenciapp.service;

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
import ar.com.buildingways.agenciapp.repository.RoleRepository;
import ar.com.buildingways.agenciapp.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

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
			User user = new User();
			if (userRepository.findByUsername(((BigDecimal) item[0]).intValue()) != null) {
				// Cuando se implemente el logueo por Active Directory, irá el nombre del usuario que ejecutó la importación del archivo de agencias.
				user.setLastModifiedBy("escobjul");
				user.setLastModifiedDate(new DateTime());
			} else {
				user.setUsername(((BigDecimal) item[0]).intValue());
				// Cuando se implemente el logueo por Active Directory, irá el nombre del usuario que ejecutó la importación del archivo de agencias.
				user.setCreatedBy("escobjul");
				user.setCreatedDate(new DateTime());
				// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				user.setRole(roleRepository.findByName("USER"));	
			}	
			user.setEnabled(true);
			
			UserDetails ud = new UserDetails();
			ud.setAddress((String) "Calle " + item[2] + " N° " + item[3] + " Localidad: " + item[4] + " C.P.: " + item[5]);
			ud.setEmail((String) item[16]);
			ud.setStoreOwner((String) item[1]);
			ud.setTradeName((String) item[15]);
			ud.setCuit(((BigDecimal) item[8]).longValue());
			ud.setCommissionAgent(((BigDecimal) item[6]).shortValue());
			ud.setTerminalQuantity(((BigDecimal) item[7]).intValue());
			ud.setCreatedBy(user.getCreatedBy());
			ud.setCreatedDate(user.getCreatedDate());
			ud.setLastModifiedBy(user.getLastModifiedBy());
			ud.setLastModifiedDate(user.getLastModifiedDate());
			ud.setDeleted(user.isDeleted());
			ud.setEnabled(user.isEnabled());
			
			user.setUserDetails(ud);
			ud.setUser(user);
						
			Account a = new Account();
			a.setAccountNumber(((BigDecimal) item[10]).intValue());
			a.setBranchNumber(((BigDecimal) item[9]).intValue());
			a.setHolder((String) item[1]);
			a.setDirectDebit((char) item[11]);
			a.setAccountType((String)item[12]);
			a.setGrossIncomePercentage(((BigDecimal) item[13]).doubleValue());
			a.setCbu((String) item[14]);
			a.setCreatedBy(user.getCreatedBy());
			a.setCreatedDate(user.getCreatedDate());
			a.setLastModifiedBy(user.getLastModifiedBy());
			a.setLastModifiedDate(user.getLastModifiedDate());
			a.setDeleted(user.isDeleted());
			a.setEnabled(user.isEnabled());

			user.setAccount(a);
			a.setUser(user);
			
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
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
}