package com.pts.managepost.Service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pts.managepost.DTO.UserDTO;
import com.pts.managepost.Entity.User;
import com.pts.managepost.Exception.MethodNotAllowed;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Repository.UserRepository;
import com.pts.managepost.Service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username).get();
		if(user == null)
		{
			 throw new UsernameNotFoundException("Username not found!");
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}
	@Override
	public UserDTO findByUsername(String username) {
		User u = this.userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("User","username",username));
		return modelMapper.map(u,UserDTO.class);
	}
	@Override
	public UserDTO save(User entity) {
		entity.setUsername(entity.getUsername().trim());
		if(this.existsByUsername(entity.getUsername()))
			return null;
		String pass =  entity.getPassword();
		entity.setPassword(this.passwordEncoder.encode(pass));
		entity.setRole(UserDTO.roleUser);
		User u =  userRepository.save(entity);
		return this.modelMapper.map(u, UserDTO.class);
	}
	@Override
	public List<UserDTO> findAll() {
		return this.userRepository.findAll().stream().map(
				user -> modelMapper.map(user, UserDTO.class)
				).collect(Collectors.toList());
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
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	@Override
	public long count() {
		return userRepository.count();
	}
	@Override
	public UserDTO update(UserDTO user) {
		user.setUsername(user.getUsername().trim());
		User u = this.userRepository.findByUsername(user.getUsername()).orElseThrow(()-> new ResourceNotFoundException("User","username",user.getUsername()));
		if(this.getUserCurrent().isActive() == true)//check active with user current
		{	
			u.setRole(user.getRole());
			this.userRepository.save(u);
			return user;	
		}else throw new MethodNotAllowed("You are not allowed");		
	}
	@Override
	public UserDTO getUserCurrent() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			  String username = ((UserDetails)principal).getUsername(); 
			  return this.findByUsername(username);
			} 
		return null;
	}

}
