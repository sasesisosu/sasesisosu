package friendsSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import friendsSite.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Query(value = "SELECT * FROM reply WHERE board_id = :boardId ORDER BY likes DESC LIMIT 1", nativeQuery = true)
	Reply replyBest(int boardId);
	
	@Query(value = "SELECT count(*) FROM reply WHERE board_id = :boardId", nativeQuery = true)
	List<Integer> replyCount(int boardId);
}
