package com.cos.blog.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplysaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. Ioc를 해준다.
// 비지니스로직은 서비스단에서 작성한다.
@Service
// @Autowired 3번째 방법
@RequiredArgsConstructor // 생성자를 만들때 초기화가 되어야하는 변수들을 생성자 파라미터에 넣어서 꼭 초기화를 하라는 어노테이션
public class BoardService {
	
	// @Autowired 2번째 방법
	// final을 사용하게되면 어떠한 값이든 초기화가 되어야 한다.
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
	// @Autowired 2번째 방법
	// 자바에서 기본생성자는 파라미터를 받는 생성자를 만드는 즉시 기본 생성자는 사라진다.
//	public BoardService(BoardRepository boardRepository, ReplyRepository replyRepository) {
//		this.boardRepository = boardRepository;
//		this.replyRepository = replyRepository;
//	}

//	@Autowired
//	private BoardRepository boardRepository;
//	
//	@Autowired
//	private ReplyRepository replyRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) { // title, content
		System.out.println(user);
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	 public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()-> {
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
	}

	@Transactional
	public void 글삭제하기(int id) {
		 boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		// 수정을 하려면 영속화를 먼저시킨다
		Board board = boardRepository.findById(id)
				.orElseThrow(()-> {
					return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
				});// 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시(Service가 종료될 때)에 트랜잭션이 종료됩니다. 이 대 더티체킹이 일어나면서 - 자동 업데이트가 됨. db flust(즉 commit이 된다.)
	}
	
	@Transactional
	public void 댓글쓰기(ReplysaveRequestDto replySaveRequestDto) {
		
//		User user = userRepository.findById(replySaveRequestDto.getUserId())
//				.orElseThrow(()-> {
//					return new IllegalArgumentException("댓글쓰기 실패: 유저ID를 찾을 수 없습니다.");
//				}); // 영속화 완료
//		
//		Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
//				.orElseThrow(()-> {
//					return new IllegalArgumentException("댓글쓰기 실패: 게시글ID를 찾을 수 없습니다.");
//				}); // 영속화 완료
		
//		Reply reply = Reply.builder()
//					.user(user)
//					.board(board)
//					.content(replySaveRequestDto.getContent())
//					.build();
//		Reply reply = new Reply();
//		reply.update(user, board, replySaveRequestDto.getContent());
		int result = replyRepository.mySave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
//		Reply reply = replyRepository.mySave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println("리조트 : " + result); // 오브젝트를 출력하게되면 자동으로 toString()이 호출된다. 기본적인 상식 같은거임
	}


	
}
