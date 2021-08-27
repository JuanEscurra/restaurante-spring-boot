package com.massimo.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.massimo.web.app.config.MyUserDetails;
import com.massimo.web.app.domain.dao.IUserDao;
import com.massimo.web.app.domain.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		if(user == null) {
			 throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(user);
	}

}
