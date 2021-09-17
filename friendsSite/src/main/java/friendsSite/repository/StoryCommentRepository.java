package friendsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import friendsSite.model.StoryComment;

public interface StoryCommentRepository extends JpaRepository<StoryComment, Integer>{

}
