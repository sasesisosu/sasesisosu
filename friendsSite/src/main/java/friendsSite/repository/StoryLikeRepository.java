package friendsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import friendsSite.model.StoryLike;

public interface StoryLikeRepository extends JpaRepository<StoryLike, Integer>{

	@Modifying
	@Query(value = "DELETE FROM story_like WHERE user_id = :userId AND story_id = :storyId", nativeQuery = true)
	void storyUnLike(int userId, int storyId);
}
