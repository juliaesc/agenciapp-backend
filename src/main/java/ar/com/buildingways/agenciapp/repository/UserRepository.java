package ar.com.buildingways.agenciapp.repository;

import ar.com.buildingways.agenciapp.model.User;
import ar.com.buildingways.agenciapp.util.SQLQueries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(Long username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value=SQLQueries.LOAD_USERS, nativeQuery=true)
    Collection<Object[]> loadUsersFromDB();

}