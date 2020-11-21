package com.erbf.bugsLife.oauth.service;

import com.erbf.bugsLife.oauth.model.User;

import java.util.List;

public interface UserService {

    public abstract User selectUser(Long id);
    public abstract List<User> selectAll();
}
