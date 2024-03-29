package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Data
public class principalDetail implements UserDetails {

	// 콤포지션 = 객체를 품고있는 것, extends로 객체를 들고오는것은 상속이라고 한다.
	private User user;
	
	public principalDetail(User user) {
		System.out.println("유저" + user);
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();		
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨 있는지 잠겨있지 않은지 리턴한다. (true: 잠겨있지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인지 리턴한다 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	} 
	
	// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 지금은 한개만 사용함!)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		// 들어올 파라미터는 정해져있다 즉 하나다 ==> ()-><람다식 사용가능!!> , GrantedAuthority안의 함수는 하나다. <람다식 사용가능!>
		collectors.add(()->{return "ROLE_"+user.getRole();}); // ROLE_을 붙여주는건 규칙이다.
		
		return collectors;
	}
	
	
	
}
