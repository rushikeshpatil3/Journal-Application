package com.rushikeshpatil.journalApp.service;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.repository.UserRepository;
import com.rushikeshpatil.journalApp.serviceImpl.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "11","2","13",
            "1","1","2",
            "2","2","3"
    })
    @DisplayName("Addition of two numbers ")
    public void testAdditionOfNumbers(int a,int b,int expected){
      assertEquals(expected,a+b);
    }


    @Test
    @DisplayName("Find By User Name ")
    public void testFindByUserName(){
        User user = userRepository.findByUserName("aaru");
        assertNotNull(user);
        assertTrue(!user.getJournalEntities().isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    @DisplayName("Create New User")
    public void testCreateUser(User user){
        assertTrue(userService.saveNewUser(user));
    }
}
