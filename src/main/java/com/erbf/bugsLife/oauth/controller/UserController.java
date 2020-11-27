package com.erbf.bugsLife.oauth.controller;


import com.erbf.bugsLife.oauth.dto.UserDto;
import com.erbf.bugsLife.oauth.exception.ResourceNotFoundException;
import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.security.CurrentUser;
import com.erbf.bugsLife.oauth.security.UserPrincipal;

import com.erbf.bugsLife.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
       return userService.selectUser(userPrincipal.getId()).toDto();
    }

    @GetMapping("/other/{id}")
    public UserDto getOtherUser(@PathVariable Long id) {
        return userService.selectUser(id).toDto();
    }

    @GetMapping("/members")
//    @PreAuthorize("hasRole('USER')")
    public List<UserDto> getUserList(){
        return userService.selectAll().stream().map(User::toDto).collect(Collectors.toList());
    }
}
