package com.pts.managepost.Controller;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.Entity.Post;
import com.pts.managepost.Entity.User;
import com.pts.managepost.Service.UserService;

@RestController
@RequestMapping("api/auth")
public class UserController {
	@Autowired
	private UserService userService;
		@PostMapping("/login")
		public ResponseEntity<?> login()
		{
			return ResponseEntity.ok("User login success!");
		}
		
		@PostMapping("/signup")
		public ResponseEntity<?> signup(@RequestBody User user)
		{
			if(this.userService.findByUsername(user.getUsername()) != null)
			{
				return ResponseEntity.badRequest().body(new String("Username is exists!"));
			}
			this.userService.save(user);
			return ResponseEntity.ok(new String("Signup is success!"));
		}
		@GetMapping("/allPost")
		public ResponseEntity<?> getAllPost(Authentication authentication)
		{
			Collection<Post> posts = this.userService.findByUsername(authentication.getName()).getPosts();
			return new ResponseEntity<>(posts,HttpStatus.OK);
		}
}
