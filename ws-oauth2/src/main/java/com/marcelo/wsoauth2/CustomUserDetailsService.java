package com.marcelo.wsoauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marcelo.wsoauth2.exceptions.ObjectNotFoundException;
import com.marcelo.wsoauth2.role.Role;
import com.marcelo.wsoauth2.user.User;
import com.marcelo.wsoauth2.user.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userService.findByEmail(email);
		
		if(!user.isPresent()) throw new UsernameNotFoundException(String.format("Usuario não existe"));
		else if(!user.get().isEnabled()) throw new ObjectNotFoundException("Usuario não esta ativo");
		
		return new UserRepositoryUserDetails(user.get());
	}
	
	private final List<GrantedAuthority> getGrantedAuthorities(final Collection<Role> roles) {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}
	
	public final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles){
		return getGrantedAuthorities(roles);
	}

	
	private final static class UserRepositoryUserDetails extends User implements UserDetails {

		public UserRepositoryUserDetails(User user) {
			super(user);
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return getRoles();
		}

		@Override
		public String getUsername() {
			return getEmail();
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
			return isUserEnabel();
		}
		
	}


}
