package com.example.oauth2.resources;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminResource {
	
	@GetMapping("/hello")
	@Secured({"ROLE_ADMIN"})
	public String hello(Principal principal) {
		return "Hi Admin," + principal.getName();
	}

}
