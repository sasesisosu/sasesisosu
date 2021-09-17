package friendsSite.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import friendsSite.model.User;
import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collector = new ArrayList<>(); 
		collector.add(()-> {
			return user.getUserRole();			
		});
		
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getUserPwd();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	


}
