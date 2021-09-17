package friendsSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import friendsSite.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO user(user_id, user_pwd, user_name, user_intro, user_relation, user_role, create_date) VALUES(:userId, :userPwd, :userName, :userIntro, :userRelation, :userRole, now())", nativeQuery = true)
	void userJoin(String userId, String userPwd, String userName, String userIntro, String userRelation, String userRole);
	
	User findByUserId(String userId);
	
}
