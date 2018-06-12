package ar.com.buildingways.agenciapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.buildingways.agenciapp.model.Role;
import ar.com.buildingways.agenciapp.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> findAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}
}