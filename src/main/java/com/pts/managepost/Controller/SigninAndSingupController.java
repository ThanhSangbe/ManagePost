package com.pts.managepost.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pts.managepost.Entity.User;
import com.pts.managepost.Service.UserService;

@RestController
@RequestMapping("")
public class SigninAndSingupController {
	@Autowired
	private UserService userService;
		@PostMapping("/login")
		public ResponseEntity<?> login()
		{
			return ResponseEntity.ok("User login success!");
		}
		
		@PostMapping("/signup")
		public ResponseEntity<?> signup(@Validated @RequestBody User user)
		{
			
			if(this.userService.save(user) == null)
			{
				return ResponseEntity.badRequest().body(new String("Username is exists!"));
			}
			return ResponseEntity.ok(new String("Signup is success!"));
		}
}
