package com.erbf.bugsLife.oauth.service.logic;

import com.erbf.bugsLife.oauth.dto.UserDto;
import com.erbf.bugsLife.oauth.exception.ResourceNotFoundException;
import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    public User selectUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        System.out.println("HERE!" + user.getEmail());
        return user;
    }

    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        users = userRepo.findAll();
        return users;
    }
}
