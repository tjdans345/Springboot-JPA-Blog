package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 시큐리티는 모든 요청을 가로챈다.
// 아래 3개의 어노테이션은 세트이다.
@Configuration // 빈 등록
@EnableWebSecurity // 필터를 거는 것 (시큐리티 필터 추가 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.) == 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService pricipalDetailService;
	
	@Bean // IoC가 돼요!! 밑에 함수들을 스프링이 관리를 함 => 필요할 때 마다 가져다가 쓸 수 있음
	public BCryptPasswordEncoder encodePWD() {
		// 아래 객체를 통해서 해쉬화를 시킬 수 있다
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(pricipalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable() //csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
				.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/iamge/**")
				.permitAll() //위 auth주로 들어오는 요청은 다 허용한다라는 의미
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다. 
//		        .failureUrl("/fail"); //로그인 실패 시 보내주는 화면
	}
	
	
}
