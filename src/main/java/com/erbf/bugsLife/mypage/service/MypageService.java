package com.erbf.bugsLife.mypage.service;

import com.erbf.bugsLife.mypage.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public interface MypageService {
    List<User> userList();
    User userDetail(Long uid);
    void userUpdate(User user) throws NoSuchElementException;
    void userCreate(User user);
}
