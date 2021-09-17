package friendsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import friendsSite.model.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{

}
