package com.pts.managepost.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.DTO.UserDTO;
import com.pts.managepost.Entity.User;
import com.pts.managepost.Exception.ResourceNotFoundException;
import com.pts.managepost.Service.UserService;

@RestController
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@GetMapping("")
	public ResponseEntity<Collection<UserDTO>> getAllUser()
	{		
		return new ResponseEntity<Collection<UserDTO>>(this.userService.findAll(),HttpStatus.OK);
	}
	@GetMapping("u")
	public ResponseEntity<?> getUserName(@RequestParam(required = false, value = "username") String username )
	{
		return ResponseEntity.ok(this.userService.findByUsername(username));
	}
	@PutMapping("update")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO user)
	{
		return ResponseEntity.ok(this.userService.update(user));
	}
	
}
