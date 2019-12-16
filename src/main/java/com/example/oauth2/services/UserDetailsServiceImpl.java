package com.example.oauth2.services;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.oauth2.entities.AppUser;
import com.example.oauth2.repository.AppUserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;

	public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<AppUser> appUsers = appUserRepository.findByUsername(username);
		if (appUsers.size() > 0) {
			AppUser appUser = appUsers.get(0);
			return new User(appUser.getUsername(), passwordNoEncoding(appUser),
					Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole())));
		}
		return null;
	}

	private String passwordNoEncoding(AppUser appUser) {
		return "{noop}" + appUser.getPassword();
	}
}
