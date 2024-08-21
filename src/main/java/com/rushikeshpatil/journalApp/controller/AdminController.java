package com.rushikeshpatil.journalApp.controller;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUserEntries = userService.getAllUserEntries();
        if (allUserEntries != null && !allUserEntries.isEmpty()) {
            return new ResponseEntity<>(allUserEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody User user) {
        User adminUser = userService.saveAdminUser(user);
        return new ResponseEntity<>(adminUser, HttpStatus.CREATED);
    }
}
