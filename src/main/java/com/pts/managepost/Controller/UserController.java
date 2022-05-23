package com.pts.managepost.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.Entity.Post;
import com.pts.managepost.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/allPost")
	public ResponseEntity<?> getAllPost(Authentication authentication)
	{
		Collection<Post> posts = this.userService.findByUsername(authentication.getName()).getPosts();
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
}
