package ar.com.buildingways.agenciapp.service;

import java.util.Collection;

import ar.com.buildingways.agenciapp.model.Role;

public interface RoleService {
	
	public Role findByName(String name);
	
	public Collection<Role> findAllRoles();
}
