package com.erbf.bugsLife.mypage.store;
import com.erbf.bugsLife.mypage.domain.user.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface MypageStore {
    User findUser(Long uid) throws NoSuchElementException;
    List<User> findAll() throws NoSuchElementException;
    void updateUser(User user) throws NoSuchElementException;
    void createUser(User user);

}
