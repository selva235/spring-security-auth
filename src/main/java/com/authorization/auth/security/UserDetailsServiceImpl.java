package com.authorization.auth.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.authorization.user.dao.userdao;
import com.authorization.user.entity.user;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private userdao userDao;
	
	 public UserDetailsServiceImpl(userdao applicationUserRepository) {
	        this.userDao = applicationUserRepository;
	    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		DetachedCriteria dc =  DetachedCriteria.forClass(user.class);
		dc.add(Restrictions.eq("username", username));
		
		user applicationUser =(user) userDao.getUserListByParam(dc).get(0);
		
		System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>"+applicationUser.getUsername()+applicationUser.getPassword());
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorities);

       
	}

}
