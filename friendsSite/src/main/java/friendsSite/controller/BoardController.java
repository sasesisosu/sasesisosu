package friendsSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import friendsSite.service.BoardService;
import friendsSite.service.ReplyService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/boardForm")
	public String boardForm(Model model, @PageableDefault(size=10, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.boardList(pageable));		
		return "board/boardForm";
	}
	
	@GetMapping("/boardBest")
	public String boardBest(Model model, @PageableDefault(size=5, sort="count", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.boardList(pageable));	
		return "board/boardBest";
	}
	
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@GetMapping("/board/{id}/{userId}")
	public String boardDetail(@PathVariable(value="id") int id, @PathVariable(value="userId") int userId, Model model) {
		model.addAttribute("board", boardService.board(id, userId));
		model.addAttribute("replyBest", replyService.replyBest(id));
		return "board/boardDetail";
	}
	
	@GetMapping("/boardDetail/{id}/{userId}")
	public String board(@PathVariable(value="id") int id, @PathVariable(value="userId") int userId, Model model) {
		model.addAttribute("board", boardService.boardDetail(id, userId));
		model.addAttribute("replyBest", replyService.replyBest(id));
		return "board/boardDetail";
	}
	
	@GetMapping("/boardUpdate/{id}")
	public String boardUpdate(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "board/boardUpdate";
	}
}
