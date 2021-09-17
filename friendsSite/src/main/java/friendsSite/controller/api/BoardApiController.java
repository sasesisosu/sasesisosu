package friendsSite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import friendsSite.config.auth.PrincipalDetails;
import friendsSite.dto.CMRespDto;
import friendsSite.dto.ReplyDto;
import friendsSite.model.Board;
import friendsSite.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/boardWrite")
	public ResponseEntity<?> boardWrite(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
		boardService.boardWrite(board, principal.getUser());	
		return new ResponseEntity<>(new CMRespDto<>(1, "글 작성 성공", null), HttpStatus.OK);
	}
	
	@DeleteMapping("/boardDelete/{id}")
	public ResponseEntity<?> boardDelete(@PathVariable int id){
		boardService.boardDelete(id);
		return new ResponseEntity<>(new CMRespDto<>(1, "글 삭제 성공", null), HttpStatus.OK);
	}
	
	@PutMapping("/boardUpdate/{id}")
	public ResponseEntity<?> boardUpdate(@PathVariable int id, @RequestBody Board board){
		boardService.boardUpdate(id, board);
		return new ResponseEntity<>(new CMRespDto<>(1, "글 수정 성공", null), HttpStatus.OK);
	}
	
	@PostMapping("/boardReply/{boardId}")
	public ResponseEntity<?> boardReply(@RequestBody ReplyDto replyDto) {
		boardService.boardReply(replyDto);	
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 작성 성공", null), HttpStatus.OK);
	}
}
