package com.pts.managepost.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	public String index()
	{
		return "Wellcome my website";
	}
	@GetMapping("/loginfail")
	public String login()
	{
		return "Username or password is incorrect!";
	}
}
