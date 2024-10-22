package com.blogging.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogging.spring.entities.User;
import com.blogging.spring.exception.UserNameNotFoundException;
import com.blogging.spring.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	// then autowired user repository here
	@Autowired
	private UserRepository uRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.uRepository.findByEmail(username)
				.orElseThrow(() -> new UserNameNotFoundException("User", "Email", username));
		return user;
	}

}
