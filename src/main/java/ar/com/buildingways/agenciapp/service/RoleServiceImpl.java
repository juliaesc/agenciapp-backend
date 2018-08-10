package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.exception.AppException;
import ar.com.buildingways.agenciapp.model.Role;
import ar.com.buildingways.agenciapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new AppException("User Role not set."));
    }

    @Override
    public List<Role> findAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }
}