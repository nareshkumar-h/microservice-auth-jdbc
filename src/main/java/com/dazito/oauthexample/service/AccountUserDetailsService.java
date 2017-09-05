package com.dazito.oauthexample.service;

import com.dazito.oauthexample.entities.Account;
import com.dazito.oauthexample.repository.AccountRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by daz on 29/06/2017.
 */
@Service
public class AccountUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	System.out.println("LoadByUsername:" + username );
        
    	Optional<Account> findByUsername = accountRepository
                .findByUsername(username);
        
		User user = findByUsername
                .map(account -> new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException("Could not find " + username));
        
        System.out.println("LoadByUsername :" + user);
		return user;
    }
}
