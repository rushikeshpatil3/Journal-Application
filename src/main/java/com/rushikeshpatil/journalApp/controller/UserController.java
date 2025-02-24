package com.rushikeshpatil.journalApp.controller;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<?> getAllUsers() {
//        List<User> allUserList = userService.getAllUserEntries();
//        if (allUserList != null && !allUserList.isEmpty()) {
//            return new ResponseEntity<>(allUserList, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userByName = userService.findUserByName(username);
        userByName.setUserName(user.getUserName());
        userByName.setPassword(user.getPassword());
        userService.saveNewUser(userByName);
        return new ResponseEntity<>(userByName, HttpStatus.OK);

    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUserByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
