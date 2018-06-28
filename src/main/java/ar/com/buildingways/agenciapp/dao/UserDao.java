package ar.com.buildingways.agenciapp.dao;

import java.util.Collection;

public interface UserDao {
	
	Collection<Object[]> loadUsersFromDB();
	
	void insertUsers(Collection<Object[]> usersFromDB);

}
