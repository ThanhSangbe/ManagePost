package com.pts.managepost.Service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pts.managepost.Entity.User;
import com.pts.managepost.Repository.UserRepository;
import com.pts.managepost.Service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if(user == null)
		{
			 throw new UsernameNotFoundException("Username not found!");
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	@Override
	public <S extends User> S save(S entity) {
		String pass =  entity.getPassword();
		entity.setPassword(this.passwordEncoder.encode(pass));
		return userRepository.save(entity);
	}
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}
	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}
	@Override
	public long count() {
		return userRepository.count();
	}

}
