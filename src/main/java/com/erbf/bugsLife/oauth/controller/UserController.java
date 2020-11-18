package com.erbf.bugsLife.oauth.controller;


import com.erbf.bugsLife.oauth.exception.ResourceNotFoundException;
import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.security.CurrentUser;
import com.erbf.bugsLife.oauth.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
       User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
       System.out.println("user-------:"+user.getRole());
       return user;
    }

    @GetMapping("/other/{id}")
    public User getOtherUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return user;
    }

    @GetMapping("/members")
    @PreAuthorize("hasRole('USER')")
    public List<User> getUserList(){
        List<User> users = userRepository.findAll();
        System.out.println("User List Request ==============");
        return users;
    }

}
