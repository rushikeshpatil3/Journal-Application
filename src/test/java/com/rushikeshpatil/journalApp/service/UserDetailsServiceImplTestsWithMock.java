package com.rushikeshpatil.journalApp.service;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.repository.UserRepository;
import com.rushikeshpatil.journalApp.serviceImpl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTestsWithMock {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void initializeMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsernameTests() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(
                User.builder().userName("aaru").password("pass").roles(new ArrayList<>()).build()
        );

        User user = userService.findUserByName("aaru");
        Assertions.assertNotNull(user);

    }
}
