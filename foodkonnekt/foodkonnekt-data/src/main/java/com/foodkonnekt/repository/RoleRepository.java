package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Find by roleName
     * 
     * @param roleName
     * @return Role instance
     */
    public Role findByRoleName(String roleName);

}
