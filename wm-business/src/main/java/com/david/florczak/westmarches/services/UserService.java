package com.david.florczak.westmarches.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.david.florczak.westmarches.config.JwtProvider;
import com.david.florczak.westmarches.dtos.UserCreate;
import com.david.florczak.westmarches.dtos.UserLogin;
import com.david.florczak.westmarches.dtos.UserLoginInfo;
import com.david.florczak.westmarches.entities.User;
import com.david.florczak.westmarches.entities.Role;
import com.david.florczak.westmarches.repositories.UserJPARepository;
import com.david.florczak.westmarches.repositories.RoleJPARepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
	
	private final UserJPARepository users;
	
	private final RoleJPARepository roles;
	
	private final PasswordEncoder encoder;
	
	private final JwtProvider provider;
	
	//injection de dependances
	public UserService(UserJPARepository users, PasswordEncoder encoder, JwtProvider provider, RoleJPARepository roles) {
		this.users = users;
		this.roles = roles;
		this.encoder = encoder;
		this.provider = provider;
	}

	@Transactional
	public void create(UserCreate inputs) {
		User user = new User();
		user.setEmail(inputs.email().toLowerCase());
		user.setUsername(inputs.email().toLowerCase());
		String hashedPassword = encoder.encode(inputs.password());
		user.setPassword(hashedPassword);
		Role defaultRole = roles.findByIsDefaultTrue();
		user.getExercisedRoles().add(defaultRole);
		users.save(user);
	}

	public UserLoginInfo login(UserLogin inputs) {
		String email = inputs.email().toLowerCase();
		String password = inputs.password();
		User fetchedUser = users.getByEmailIgnoreCase(email)
									.orElseThrow(() -> new BadCredentialsException("invalid email"));
		
		if(!encoder.matches(password, fetchedUser.getPassword())) {
			throw new BadCredentialsException("invalid password");
		}

		Set<Role> sentRoles = roles.findAllByExercisingUsersEmail(email); 

		return provider.createUserLoginInfo(email, sentRoles);
	}

	public Optional<User> getUser(String name) {
		return users.getByEmailIgnoreCase(name);
	}

	public boolean existsByEmail(String value) {
		return users.existsByEmail(value);
	}

}