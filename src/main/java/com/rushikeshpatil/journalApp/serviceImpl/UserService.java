package com.rushikeshpatil.journalApp.serviceImpl;

import com.rushikeshpatil.journalApp.entity.User;
import com.rushikeshpatil.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final PasswordEncoder PASSWORD_ENCODER=new BCryptPasswordEncoder();


    public boolean saveNewUser(User user) {
        try {
            user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
             userRepository.save(user);
             return true;
        }catch(Exception e){
            return false;
        }
    }

    public User saveUserEntries(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUserEntries(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findUserByName(String userName){
        User byUserName = userRepository.findByUserName(userName);
        return byUserName;
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUserName(username);
    }


    public User saveAdminUser(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        return userRepository.save(user);
    }
}
