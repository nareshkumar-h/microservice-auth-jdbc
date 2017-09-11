package com.naresh.authservice.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.authservice.dto.CustomUser;

@RestController
public class AuthController {

	@RequestMapping("/user")
	public Principal oauth(Principal principal) {
		/*
		 * Translate the incoming request, which has an access token Spring security
		 * takes the incoming request and injects the Java Security Principal The
		 * converter inside Spring Security will handle the to json method which the
		 * Spring Security Oauth client will know how to read
		 *
		 * The @EnableResourceServer on the application entry point is what makes all
		 * this magic happen. If there is an incoming request token it will check the
		 * token validity and handle it accordingly
		 */


		return principal;
	}
	
	@GetMapping("/currentuser")
	public CustomUser getCurrentUser() {
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUser user = (CustomUser) authentication.getPrincipal();
		System.out.println("Get User:" + user);

		return user;
	}
}
