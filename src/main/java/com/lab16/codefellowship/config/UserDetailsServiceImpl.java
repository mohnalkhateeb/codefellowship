package com.lab16.codefellowship.config;

import com.lab16.codefellowship.models.ApplicationUser;
import com.lab16.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  ApplicationUserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser user =  userRepository.findApplicationUser(username);
    if (user == null) {
      System.out.print("Username not found");
      throw new UsernameNotFoundException((username + " not found"));
    }

    return user;
  }
}
