package com.naresh.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naresh.authservice.dto.CustomUser;
import com.naresh.authservice.model.User;
import com.naresh.authservice.repository.UserRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private UserRepository accountRepository;

    @Autowired
    public AccountUserDetailsService(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	System.out.println("LoadByUsername:" + username );
        
    	Optional<User> findByUsername = accountRepository
                .findByUsername(username);
        
    	CustomUser user = findByUsername
                .map(account -> new CustomUser(account.getId(), account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN")))
                .orElseThrow(() -> new UsernameNotFoundException("Could not find " + username));
        
        System.out.println("LoadByUsername :" + user);
		return user;
    }
}
