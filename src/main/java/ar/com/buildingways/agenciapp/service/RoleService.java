package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.model.Role;

import java.util.Collection;

public interface RoleService {
	
	public Role findByName(String name);
	
	public Collection<Role> findAllRoles();
}
