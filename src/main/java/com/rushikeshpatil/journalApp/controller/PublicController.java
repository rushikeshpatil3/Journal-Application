package com.rushikeshpatil.journalApp.controller;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicController {

    @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
    User user = userService.findUserByName(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
