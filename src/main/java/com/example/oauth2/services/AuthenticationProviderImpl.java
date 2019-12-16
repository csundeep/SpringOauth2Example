package com.example.oauth2.services;

import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.oauth2.entities.AppUser;
import com.example.oauth2.repository.AppUserRepository;

public class AuthenticationProviderImpl implements AuthenticationProvider {
	private final AppUserRepository appUserRepository;

	public AuthenticationProviderImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getName() == null || authentication.getCredentials() == null)
			return null;
		List<AppUser> appUsers = appUserRepository.findByUsername(authentication.getName());
		if (appUsers.size() > 0) {
			AppUser appUser = appUsers.get(0);
			if (authentication.getName().equalsIgnoreCase(appUser.getUsername())
					&& authentication.getCredentials().equals(appUser.getPassword()))
				return new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword(),
						Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole())));
		}
		throw new UsernameNotFoundException("Invalid Username and password");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
