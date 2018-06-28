package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDaoImpl;
	
	/** Actualiza diariamente la BD de usuarios recuperando las agencias activas y sus datos
	 * por medio de una vista que apunta a varias BD de Lotería.
	 */
	@Override
	public void updateUsers() {
		Collection<Object[]> usersFromDB = userDaoImpl.loadUsersFromDB();
		userDaoImpl.insertUsers(usersFromDB);
	}

}