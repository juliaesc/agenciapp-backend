package ar.com.buildingways.agenciapp.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

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
import ar.com.buildingways.agenciapp.util.Constants;
import ar.com.buildingways.agenciapp.util.SQLQueries;

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
	public void updateUsers() {
		Collection<Object[]> queryResults = loadQueryResults();
		insertUsers(queryResults);
	}
	
	private Collection<Object[]> loadQueryResults() {
		Query query = entityManager.createNativeQuery(SQLQueries.LOAD_USERS);
		@SuppressWarnings("unchecked")
		Collection<Object[]> queryResults = query.getResultList();
		return queryResults;		
	}
		
	private void insertUsers(Collection<Object[]> queryResults) {
		Iterator<Object[]> it = queryResults.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[])it.next();
			User user = userRepository.findByUsername(((BigDecimal) item[0]).intValue());
			if (user != null) {
				user.setLastModifiedBy("escobjul");
				user.setLastModifiedDate(new DateTime());
			} else {
				user = new User(((BigDecimal) item[0]).intValue(), "escobjul", new DateTime());
				// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
			}	 
			user.setRole(roleRepository.findByName(Constants.ROL_USUARIO));
			user.setEnabled(true);	
			UserDetails ud = createUserDetails(item, user);	
			Account a = createAccount(item, user);
			user.setAccount(a);
			user.setUserDetails(ud);
			userRepository.save(user);
		}
	}
	
	private UserDetails createUserDetails(Object[] item, User user) {
		UserDetails ud = new UserDetails((String) item[2], (String) item[13], (String) item[1], (String) item[12], 
				((BigDecimal) item[5]).longValue(), ((BigDecimal) item[3]).intValue(), ((BigDecimal) item[4]).shortValue(),
				user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(),
				user.isEnabled(), user.isDeleted());
		ud.setUser(user);
		ud.setUserId(user.getId());
		return ud;
	}
	
	private Account createAccount(Object[] item, User user) {
		Account a = new Account(((BigDecimal) item[7]).intValue(), ((BigDecimal) item[6]).intValue(),
				(String) item[1], (char) item[8], (String)item[9], ((BigDecimal) item[10]).doubleValue(), (String) item[11],
				user.getCreatedBy(),user.getCreatedDate(),user.getLastModifiedBy(),user.getLastModifiedDate(),
				user.isEnabled(),user.isDeleted());
		a.setUser(user);
		a.setUserId(user.getId());
		return a;
	}

}