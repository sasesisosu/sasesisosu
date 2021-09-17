package friendsSite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import friendsSite.config.auth.PrincipalDetails;
import friendsSite.dto.CMRespDto;
import friendsSite.service.ReplyService;

@RestController
public class ReplyApiController {
	
	@Autowired
	private ReplyService replySerivce;
	
	@DeleteMapping("/replyDelete/{boardId}/{replyId}")
	public ResponseEntity<?> replyDelete(@PathVariable(value="replyId") int replyId, @PathVariable(value="boardId") int boardId, @AuthenticationPrincipal PrincipalDetails principal) {
		replySerivce.replyDelete(replyId, boardId, principal.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 삭제 성공", null), HttpStatus.OK);
	}
	
}
