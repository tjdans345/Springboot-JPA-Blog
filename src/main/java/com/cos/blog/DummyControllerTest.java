package com.cos.blog;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	 
	@Autowired
	private UserRepository userRepository;
	
	// http://localhost:8090/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// http://localhost:8090/blog/dummy/user/page
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	// Pageable 페이지 가능~
	// getContent로 필요 Data(실제 유저정보)만 보내더라도 쿼리스트링으로 페이징처리 가능
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC)Pageable pageable ) {
		Page<User> pageUsers = userRepository.findAll(pageable);
		List<User> users = pageUsers.getContent();
		return users;
	}
	
	// {id} 주소로 파라메터를 전달 받을 수 있음.
	// http://localhost:8090/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return값이 null이 될거고 프로그램에 문제가 생길 수 있으니
		// Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!!!!!!!
		// 인터페이스를 new하려면 익명 함수를 만들어야한다. 람다와 인터페이스 관계 공부 다시 한번 더 해놓자!
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
			//리턴 타입이 Optional이다 //.get()은 null값이 절대 아니다라고 명시해주는것 플러터에서 !같은 느낌
		}); 
		
		//람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("핻장 사용자는 없습니다.");
//		});
		
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리 : 자바 오브젝트 -> json으로 변경)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 messageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	// http://localhost:8090/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key = value (약속된 규칙)
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getRole());
		System.out.println(user.getCreateDate());
		
		// 잘못된 값을 방지하기 위해 Enum을 사용한다 // Enum을 사용하여 실수를 방지한다
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	@Transactional // 해당 컨트롤러가 종료될 때 트랜잭션도 종료가 된다. 종료가 될 때 변경이 감지 됨 / 함수 종료시에 자동으로 commit이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		//json데이터를 요청 => Java Object(MessageConverter의 Jackson라이버리가 자바 오브젝트로 변환해서 받아 줌
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("emmail : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		// userRepository.save(requestUser);
		
		//flush : 데이터를 데이터를 넣는 것 (프로그래밍에서는 buffer를 비운다라고 한다.)
		// 더티 체킹 (변경이 감지 되었을 때 DB Data를 변경 후 커밋함)
		// DB에서는 데이터를 모아뒀다가 한번에 커밋 하는것을 더티 체킹이라한다.
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제 되었습니다.";
	}
	
	

}
