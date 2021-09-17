package friendsSite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import friendsSite.dto.ReplyDto;
import friendsSite.model.Board;
import friendsSite.model.Reply;
import friendsSite.model.User;
import friendsSite.repository.BoardRepository;
import friendsSite.repository.ReplyRepository;
import friendsSite.repository.UserRepository;

@Service
public class BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReplyRepository replyRepository; 
	
	@Transactional
	public void boardWrite(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		board.setReplyCount(0);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly=true)
	public Page<Board> boardList(Pageable pageable){
		return boardRepository.findAll(pageable); 
	}
		
	@Transactional
	public Board boardDetail(int boardId, int userId) {
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			log.error("Method : boardDetail, Message : 게시글 찾기 실패" );
			return new IllegalArgumentException("게시글 찾기 실패");
		});

		return board;
	}
	
	@Transactional
	public Board board(int boardId, int userId) {
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			log.error("Method : board, Message : 게시글 찾기 실패" );
			return new IllegalArgumentException("게시글 찾기 실패");
		});
		if(board.getUser().getId() != userId) {
			board.setCount(board.getCount()+1);
		}

		return board;
	}
	
	@Transactional(readOnly=true)
	public Board boardDetail(int boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			log.error("Method : boardDetail, Message : 게시글 찾기 실패" );
			return new IllegalArgumentException("게시글 찾기 실패");
		});
		return board;
	}
	
	@Transactional
	public void boardDelete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void boardUpdate(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(()->{
			log.error("Method : boardUpdate, Message : 게시글 찾기 실패" );
			return new IllegalArgumentException("게시글 찾기 실패");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		
	}
	
	@Transactional
	public void boardReply(ReplyDto replyDto) {
		
		User user = userRepository.findById(replyDto.getUserId()).orElseThrow(()->{
			log.error("Method : boardReply, Message : 유저 찾기 실패" );
			return new IllegalArgumentException("유저 찾기 실패");
		});
		
		Board board = boardRepository.findById(replyDto.getBoardId()).orElseThrow(()->{
			log.error("Method : boardReply, Message : 게시글 찾기 실패" );
			return new IllegalArgumentException("게시글 찾기 실패");
		});
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replyDto.getContent())
				.likes(0)
				.hate(0)
				.build();
		
		board.setReplyCount(board.getReplyCount()+1);
		
//		requestReply.setUser(user);
//		requestReply.setBoard(board);
//		requestReply.setLikes(0);
//		requestReply.setHate(0);
//		requestReply.setReReply(false);

		replyRepository.save(reply);
	}
}
