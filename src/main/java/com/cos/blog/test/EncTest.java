package com.cos.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test
	public void 해쉬_암화화() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println("1234 해쉬 : "+encPassword);
	}

}
