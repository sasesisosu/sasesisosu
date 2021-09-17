package friendsSite.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import friendsSite.model.User;
import friendsSite.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUserId(username);
		System.out.println("username : " + username);
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
	} 
	

}
