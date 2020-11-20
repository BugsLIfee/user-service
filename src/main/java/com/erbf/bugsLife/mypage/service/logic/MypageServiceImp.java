package com.erbf.bugsLife.mypage.service.logic;

import com.erbf.bugsLife.mypage.domain.user.User;
import com.erbf.bugsLife.mypage.service.MypageService;
import com.erbf.bugsLife.mypage.store.MypageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MypageServiceImp implements MypageService {

private final MypageStore store;


    @Override
    public List<User> userList() {
       return store.findAll();
    }

    @Override
    public User userDetail(Long uid) {
       return store.findUser(uid);
    }

    @Override
    public void userUpdate(User user) throws NoSuchElementException {
        store.updateUser(user);
    }

    @Override
    public void userCreate(User user) {
        store.createUser(user);
    }
}
