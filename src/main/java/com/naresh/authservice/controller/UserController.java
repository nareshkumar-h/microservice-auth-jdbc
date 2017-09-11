package com.naresh.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.authservice.dto.UserDTO;
import com.naresh.authservice.service.UserService;

//@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDTO> index() {
		return userService.list();
	}
	
	

	@GetMapping("/{userId}")
	public UserDTO findOne(@PathVariable("userId") Long userId) {
		return userService.findByUserId(userId);
	}

	/*
	 * @PostMapping("/login") public UserDTO login(@RequestBody UserDTO userDTO) {
	 * System.out.println("UserController->login->" + userDTO); UserDTO dto = null;
	 * Optional<User> findByUsername =
	 * userService.findByUsername(userDTO.getUsername()); if
	 * (findByUsername.isPresent()) { dto = new UserDTO();
	 * dto.setName(findByUsername.get().getUsername()); }
	 * 
	 * return dto; }
	 */

}
