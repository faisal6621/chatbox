package com.faisal.chatbox.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginService implements UserDetailsService
{
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
	{
		System.out.println( "in loginService" );
		return new User( username, "", AuthorityUtils.createAuthorityList( "ROLE_USER" ) );
	}
}
