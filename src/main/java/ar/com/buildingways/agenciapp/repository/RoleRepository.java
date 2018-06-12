package ar.com.buildingways.agenciapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.buildingways.agenciapp.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByName(String name);

}