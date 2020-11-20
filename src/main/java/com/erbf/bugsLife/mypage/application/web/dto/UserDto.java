package com.erbf.bugsLife.mypage.application.web.dto;

import com.erbf.bugsLife.mypage.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
public class UserDto {

    private Long uid;
    private String email;
    private String name;
    private int point;
    private int level;
    private String enrollDate;

    @Builder
    public UserDto(Long uid, String email, String name) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        int default_value = 1;

//        String uid = UUID.randomUUID().toString();
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.point = default_value;
        this.level = default_value;
        this.enrollDate = sdf.format(date);
    }

    public User toEntitiy(){
        return User.builder().uid(this.uid).email(email).name(name).build();
    }
}
