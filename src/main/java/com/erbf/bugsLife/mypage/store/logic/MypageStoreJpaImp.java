package com.erbf.bugsLife.mypage.store.logic;

import com.erbf.bugsLife.mypage.domain.user.User;
import com.erbf.bugsLife.mypage.store.MypageStore;
import com.erbf.bugsLife.mypage.store.repository.MypageStoreJpaRepository;
import com.erbf.bugsLife.mypage.store.repository.UserJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class MypageStoreJpaImp implements MypageStore {

    @Autowired
    MypageStoreJpaRepository repo;

    @Override
    public User findUser(Long uid) {
        Optional<UserJpo> userJpo = repo.findByUid(uid);
        return userJpo.map(UserJpo::toDomain).get();
    }

    @Override
    public List<User> findAll() {
        List<UserJpo> users = repo.findAll();
        return UserJpo.toDomains(users);
    }

    @Override
    public void updateUser(User user) throws NoSuchElementException {
       UserJpo update_user = UserJpo.builder().email(user.getEmail()).name(user.getName()).build();
        repo.save(update_user);
    }

    @Override
    public void createUser(User user) {
        UserJpo new_user = UserJpo.builder().email(user.getEmail()).name(user.getName()).build();
        repo.save(new_user);
    }

}

