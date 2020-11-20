package com.erbf.bugsLife.mypage.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MypageStoreJpaRepository extends JpaRepository<UserJpo, Long> {

    Optional<UserJpo> findByUid(Long uid);

}
