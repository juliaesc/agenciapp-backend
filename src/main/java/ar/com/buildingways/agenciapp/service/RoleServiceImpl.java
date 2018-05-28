package ar.com.buildingways.agenciapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.model.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Override
	public Role findByRole(String role) {
		return null;
	}

	@Override
	public List<Role> findAllRoles() {
		return null;
	}

}
