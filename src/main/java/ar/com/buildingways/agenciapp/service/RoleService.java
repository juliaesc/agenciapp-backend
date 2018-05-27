package ar.com.buildingways.iplycbackend.service;

import java.util.List;
import ar.com.buildingways.iplycbackend.model.Role;

public interface RoleService {
	 
    Role findByRole(String name);
     
    List<Role> findAllRoles();

}
