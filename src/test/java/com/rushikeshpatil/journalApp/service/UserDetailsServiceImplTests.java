package com.rushikeshpatil.journalApp.service;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.repository.UserRepository;
import com.rushikeshpatil.journalApp.serviceImpl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameTests(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("aaru").password("dontknow").build());
        UserDetails user = userDetailsService.loadUserByUsername("aaru");
        assertNotNull(user);    }
}
