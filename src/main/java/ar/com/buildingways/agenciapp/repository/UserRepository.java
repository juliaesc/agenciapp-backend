package ar.com.buildingways.agenciapp.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.buildingways.agenciapp.dao.UserRepositoryCustom;
import ar.com.buildingways.agenciapp.model.User;

public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom {

	User findByUsername(int username);

}