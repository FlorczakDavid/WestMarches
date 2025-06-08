package com.david.florczak.westmarches.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.florczak.westmarches.entities.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {

	Optional<User> getByEmailIgnoreCase(String username);

	boolean existsByEmail(String value);

}
