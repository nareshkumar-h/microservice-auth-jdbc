package com.naresh.authservice.dto;

import java.util.Collection;
import java.util.StringJoiner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	private String name;
	private String roles;
	
	@Override
	public String toString() {
		return "CustomUser [name=" + name + ", roles=" + roles + ", id=" + id + "]";
	}

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Long userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = userId;
		this.name = username;
		this.roles = getUserRoles(authorities);
	}

	public CustomUser(Long userId, String username, String password, Collection<? extends GrantedAuthority> createAuthorityList) {
		super(username,password,createAuthorityList);
		this.id = userId;
		this.name = username;
		this.roles = getUserRoles(createAuthorityList);
	}


	public String getName() {
		return name;
	}
	
	public String getUserRoles(Collection<? extends GrantedAuthority> createAuthorityList)
	{
		StringJoiner sj = new StringJoiner(",");
		for (GrantedAuthority grantedAuthority : createAuthorityList) {
			sj.add(grantedAuthority.getAuthority());
		}
		return sj.toString();
		
	}


	private Long id;
	

	public String getRoles() {
		return roles;
	}

}
