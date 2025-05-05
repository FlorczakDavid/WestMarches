package com.david.florczak.westmarches.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.entities.Role;

@Repository
public interface RoleJPARepository extends JpaRepository<Role, Long>{

	Role findByIsDefaultTrue();
	
	Set<Role> findAllByExercisingUsersEmail(String unsername);

}