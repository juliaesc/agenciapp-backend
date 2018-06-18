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
import ar.com.buildingways.agenciapp.utils.Constants;
import ar.com.buildingways.agenciapp.utils.SQLQueries;

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
		Collection<Object[]> users = loadUsers();
		insertUsers(users);	
	}
	
	private Collection<Object[]> loadUsers() {
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
				// Cuando se implemente el logueo por Active Directory, irá el nombre del usuario que ejecutó la importación del archivo de agencias.
				user.setLastModifiedBy("escobjul");
				user.setLastModifiedDate(new DateTime());
			} else {
				user = new User();
				user.setUsername(((BigDecimal) item[0]).intValue());
				// Cuando se implemente el logueo por Active Directory, irá el nombre del usuario que ejecutó la importación del archivo de agencias.
				user.setCreatedBy("escobjul");
				user.setCreatedDate(new DateTime());
				// Definir con qué credenciales se va a loguear.
				// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		
			}	
			user.setRole(roleRepository.findByName(Constants.ROL_USUARIO));
			user.setEnabled(true);
			
			UserDetails ud = new UserDetails((String) item[2] + " N° " + item[3] + " Localidad: " + item[4] + " C.P.: " + item[5],
					(String) item[16],(String) item[1],(String) item[15],((BigDecimal) item[8]).longValue(),
					((BigDecimal) item[6]).shortValue(),((BigDecimal) item[7]).intValue(),
					user.getCreatedBy(),user.getCreatedDate(),user.getLastModifiedBy(),user.getLastModifiedDate(),
					user.isEnabled(),user.isDeleted());
			
			user.setUserDetails(ud);
			ud.setUser(user);
			ud.setUserId(user.getId());
						
			Account a = new Account(((BigDecimal) item[10]).intValue(),((BigDecimal) item[9]).intValue(),
					(String) item[1],(char) item[11],(String)item[12],((BigDecimal) item[13]).doubleValue(),(String) item[14],
					user.getCreatedBy(),user.getCreatedDate(),user.getLastModifiedBy(),user.getLastModifiedDate(),
					user.isEnabled(),user.isDeleted());

			user.setAccount(a);
			a.setUser(user);
			a.setUserId(user.getId());
			
			userRepository.save(user);
		}
	}

}