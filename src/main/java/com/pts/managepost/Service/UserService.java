package com.pts.managepost.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pts.managepost.DTO.UserDTO;
import com.pts.managepost.Entity.User;

public interface UserService extends UserDetailsService  {

	long count();

	boolean existsById(Integer id);

	Optional<User> findById(Integer id);

	List<UserDTO> findAll();

	 UserDTO  save(User entity);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	UserDTO findByUsername(String username);

	boolean existsByUsername(String username);
	UserDTO update(UserDTO user);
	UserDTO getUserCurrent();
}
