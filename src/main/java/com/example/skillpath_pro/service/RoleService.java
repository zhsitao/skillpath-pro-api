package com.example.skillpath_pro.service;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long roleId, Role updatedRole) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setTitle(updatedRole.getTitle());
        role.setCategory(updatedRole.getCategory());
        return roleRepository.save(role); // Save the updated role in the database
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId); // Delete the role by its ID
    }

}
