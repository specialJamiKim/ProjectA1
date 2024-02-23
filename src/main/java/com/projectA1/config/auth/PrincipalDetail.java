package com.projectA1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectA1.model.Owner;
import com.projectA1.model.User;
import com.projectA1.repository.OwnerRepository;
import com.projectA1.repository.UserRepository;

@Service
public class PrincipalDetail  implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
	   User user = 	userRepository.findByUsername(username);

	   if(user != null) return null;
	   //회원이라면 시큐리티를 적용한  user 리턴
	   PrincipalUser puser = new PrincipalUser(user);
		return puser;
	}

}
