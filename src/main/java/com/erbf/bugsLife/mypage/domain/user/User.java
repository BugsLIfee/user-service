package com.erbf.bugsLife.mypage.domain.user;

import com.erbf.bugsLife.mypage.application.web.dto.UserDto;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class User {
//    @Id
    private Long uid;
    private String email;
    private String name;
    private int point;
    private int level;
    private String enrollDate;

    @Builder
    public User( Long uid, String email, String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        int default_value = 1;
//
//        String uid = UUID.randomUUID().toString();
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.point = default_value;
        this.level = default_value;
        this.enrollDate = sdf.format(date);
    }


    @Override
    public String toString() {
        Gson json = new Gson();
        return json.toJson(this);
    }

    public UserDto toDto(){
        return UserDto.builder().uid(this.uid).email(this.email).name(this.name).build();
    }

    public static List<UserDto> toDtoList(List<User> users){
      return  users.stream().map(User::toDto).collect(Collectors.toList());
    }

}
