package com.example.expense.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.expense.entities.UserInfo;

public class CustomUserDetails extends UserInfo implements UserDetails {
	
	private String username;
	private String password;
	
	Collection<? extends GrantedAuthority> authorities; 
	
	public CustomUserDetails(UserInfo byUserName) {

		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
