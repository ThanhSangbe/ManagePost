package com.pts.managepost.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pts.managepost.Entity.User;

public interface UserService extends UserDetailsService  {

	long count();

	boolean existsById(Integer id);

	Optional<User> findById(Integer id);

	List<User> findAll();

	<S extends User> S save(S entity);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	User findByUsername(String username);

}
