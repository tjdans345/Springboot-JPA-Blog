package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 시큐리티는 모든 요청을 가로챈다.
// 아래 3개의 어노테이션은 세트이다.
@Configuration // 빈 등록
@EnableWebSecurity // 필터를 거는 것 (시큐리티 필터 추가 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.) == 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/auth/**")
			.permitAll() //위 auth주로 들어오는 요청은 다 허용한다라는 의미
			.anyRequest()
			.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
		
	}

	
	
	
}
