package com.rushikeshpatil.journalApp.serviceImpl;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user!=null){
//            System.out.println(user.getRoles().toArray(new String[0]));
            UserDetails build = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();
            return build;
        }
        throw new UsernameNotFoundException("User Not Found With Username : "+username);
    }
}
