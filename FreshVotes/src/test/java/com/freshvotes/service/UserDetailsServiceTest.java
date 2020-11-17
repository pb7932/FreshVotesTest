package com.freshvotes.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserDetailsServiceTest {

	@Test
	public void genereta_encrypted_password() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "asdf";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		
		assertThat(rawPassword, not(encodedPassword));
	}
}
