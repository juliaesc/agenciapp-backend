package ar.com.buildingways.agenciapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.buildingways.agenciapp.dao.UserDao;
import ar.com.buildingways.agenciapp.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer>, UserDao {

	User findByUsername(int username);

}