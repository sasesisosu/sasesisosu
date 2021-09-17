package friendsSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import friendsSite.model.ReplyLikeHate;

public interface ReplyLikeHateRepository extends JpaRepository<ReplyLikeHate, Integer>{

	@Query(value = "SELECT reply_id FROM reply_like_hate WHERE user_id = :userId", nativeQuery = true)
	List<Integer> likeHateReplyId(int userId);
	
	@Query(value = "SELECT like_hate FROM reply_like_hate WHERE user_id = :userId AND reply_id = :replyId", nativeQuery = true)
	boolean likeHate(int userId, int replyId);
	
	@Modifying
	@Query(value = "DELETE FROM reply_like_hate WHERE user_id = :userId AND reply_id = :replyId", nativeQuery = true)
	void replyLikeHateDelete(int userId, int replyId);
}
