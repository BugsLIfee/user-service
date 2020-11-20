package com.erbf.bugsLife.mypage.store.repository;

import com.erbf.bugsLife.mypage.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name="users_kaya")
@NoArgsConstructor
//@SequenceGenerator(
//        name="USER_SEQ_GEN", //시퀀스 제너레이터 이름
//        sequenceName="todo_seq", //시퀀스 이름
//        initialValue=1, //시작값
//        allocationSize=1//메모리를 통해 할당할 범위 사이즈-default:50
//)
public class UserJpo {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="UID", unique = true, nullable = false)
    private Long uid;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private int point;
    private int level;
    private String enrollDate;

    @Builder
    public UserJpo( String email, String name) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();
      int default_value = 1;


        this.uid = uid;
        this.email = email;
        this.name = name;
        this.point = default_value;
        this.level = default_value;
        this.enrollDate = sdf.format(date);
    }

//    private long likes;
//    private long posts;
//    private long comments;
//    private String status;
//    private long attendance;

    public User toDomain() {
        User user = User.builder().
                uid(this.uid).email(this.email).name(this.name).build();
        return user;
    }

    public static List<User> toDomains(List<UserJpo> users){
        return users.stream().map(UserJpo::toDomain).collect(Collectors.toList());
    }

}
