package com.naresh.authservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.authservice.dto.UserDTO;
import com.naresh.authservice.model.User;
import com.naresh.authservice.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired	
	private UserRepository userRepository;
	
	
	public List<UserDTO> list(){
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		for (User user : userList) {
			UserDTO dto = toDTO(user);
			userDTOList.add(dto);
		}
		return userDTOList;
	}
	
	public UserDTO findByUserId(Long userId) {
		User findOne = userRepository.findOne(userId);
		return toDTO(findOne);
	}
	
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);				
	}
	
	
	private UserDTO toDTO(User user) {
		UserDTO u = new UserDTO();
		u.setId(user.getId());
		u.setName(user.getName());
		u.setUsername(user.getUsername());
		return u;
	}
	
 }
