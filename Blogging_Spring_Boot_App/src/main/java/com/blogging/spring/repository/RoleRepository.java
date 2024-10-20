package com.blogging.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.spring.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
