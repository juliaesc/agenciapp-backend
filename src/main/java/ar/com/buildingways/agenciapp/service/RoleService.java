package ar.com.buildingways.agenciapp.service;

import java.util.List;
import ar.com.buildingways.agenciapp.model.Role;

public interface RoleService {
	 
    Role findByRole(String name);
     
    List<Role> findAllRoles();

}
