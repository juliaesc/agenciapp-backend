package ar.com.buildingways.iplycbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.buildingways.iplycbackend.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(int username);

}