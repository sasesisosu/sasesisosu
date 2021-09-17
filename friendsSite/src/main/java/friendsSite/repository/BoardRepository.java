package friendsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import friendsSite.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	
//	Board findById(int id);
	
}
