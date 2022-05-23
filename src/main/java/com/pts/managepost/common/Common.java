package com.pts.managepost.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pts.managepost.DTO.UserDTO;
import com.pts.managepost.Service.UserService;

@ControllerAdvice
public class Common {
	@Autowired
	private UserService userService;
	@ModelAttribute("currentUser")
	public UserDetails getCurrentUser(Authentication authentication)
	{
		return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
	}
//	public UserDTO getUserCurrent() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			  String username = ((UserDetails)principal).getUsername(); 
//			  
//			  return this.userService.findByUsername(username);
//			} 
//		return null;
//	}
}
