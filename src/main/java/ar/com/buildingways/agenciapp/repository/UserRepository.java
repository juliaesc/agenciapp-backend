package ar.com.buildingways.agenciapp.repository;

import ar.com.buildingways.agenciapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	//User findByUsername(int username);
	
	Optional<User> findByUsername(Long username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);


}