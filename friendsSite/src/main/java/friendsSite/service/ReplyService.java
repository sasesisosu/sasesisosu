package friendsSite.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import friendsSite.model.Board;
import friendsSite.model.Reply;
import friendsSite.model.ReplyLikeHate;
import friendsSite.repository.BoardRepository;
import friendsSite.repository.ReplyLikeHateRepository;
import friendsSite.repository.ReplyRepository;

@Service
public class ReplyService {
	
	private static final Logger log = LoggerFactory.getLogger(ReplyService.class);
	
	@Autowired
	private ReplyRepository replyRepository; 
	
	@Autowired
	private ReplyLikeHateRepository replyLikeHateRepository;

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void replyLike(int replyId, int boardId, int userId, int likeHate) {

		Reply reply = replyRepository.findById(replyId).orElseThrow(()->{
			log.error("Method : replyLike, Message : 댓글 찾기 실패" );
			return new IllegalArgumentException("댓글 찾기 실패");
		});

		List<Integer> likeHateReplyId = replyLikeHateRepository.likeHateReplyId(userId);		

		boolean likeHateInfo = true;
		int replyExistence = 0;
		for(int check : likeHateReplyId) {
			if(replyId == check) {
				likeHateInfo = replyLikeHateRepository.likeHate(userId, replyId);
				replyExistence = 1;
			}
		}

		ReplyLikeHate replyLikeHateEntity = new ReplyLikeHate();

		if(replyExistence == 1) {
			if(likeHateInfo == true) {
				if(likeHate == 1) {
					replyLikeHateRepository.replyLikeHateDelete(userId, replyId);
					reply.setLikes(reply.getLikes() -1 );
				}			
			}else {
				if(likeHate == 0) {
					replyLikeHateRepository.replyLikeHateDelete(userId, replyId);
					reply.setHate(reply.getHate() - 1);
				}
			}
		}else {
			if(likeHate == 1) {
				replyLikeHateEntity.setLikeHate(true);
				reply.setLikes(reply.getLikes() + 1);
			}else {
				replyLikeHateEntity.setLikeHate(false);
				reply.setHate(reply.getHate() + 1);
			}
			replyLikeHateEntity.setReplyId(replyId);
			replyLikeHateEntity.setUserId(userId);
			replyLikeHateRepository.save(replyLikeHateEntity);
		}
	}
	
	@Transactional
	public Reply replyBest(int boardId) {
		Reply replyBest = replyRepository.replyBest(boardId);
		return replyBest;
	}
	
	@Transactional
	public void replyDelete(int replyId, int boardId, int userId) {
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			log.error("Method : replyDelete, Message : 게시글 찾기 실패" );
			return new IllegalArgumentException("게시글 찾기 실패");
		});
		board.setReplyCount(board.getReplyCount()-1);
		replyLikeHateRepository.replyLikeHateDelete(userId, replyId);
		replyRepository.deleteById(replyId);
	}
	
}
