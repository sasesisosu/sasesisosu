package friendsSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import friendsSite.service.BoardService;
import friendsSite.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private BoardService boardService;
	
	
//	@GetMapping("/replyLike/{replyId}/{boardId}/{userId}")
//	public String replyLike(@PathVariable(value="replyId") int replyId, @PathVariable(value="boardId") int boardId,
//					@PathVariable(value="userId") int userId) {
//		System.out.println("replyId : " + replyId);
//		System.out.println("boardId : " + boardId);
//		System.out.println("userId : " + userId);
//		replySerivce.replyLike(replyId, boardId, userId);
//		System.out.println("zzzzzzzzzzzzzz23333333333333");
//		return "board/"+boardId+"/"+userId;
//	}
	
	@GetMapping("/replyLike/{replyId}/{boardId}/{userId}/{LikeHate}")
	public String replyLike(@PathVariable(value="replyId") int replyId, @PathVariable(value="boardId") int boardId,
					@PathVariable(value="userId") int userId, @PathVariable(value="LikeHate") int LikeHate, Model model) {

		replyService.replyLike(replyId, boardId, userId, LikeHate);
		model.addAttribute("board", boardService.boardDetail(boardId, userId));
		model.addAttribute("replyBest", replyService.replyBest(boardId));
		return "board/boardDetail";
	}


	
}
