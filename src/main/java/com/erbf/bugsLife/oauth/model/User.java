package com.erbf.bugsLife.oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Table(name = "oauth_user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)   
    private String email;

    private String imageUrl;

   // @Column(nullable = false)
    private Boolean emailVerified = false;

    private Boolean isAttend = false;
    private int attendCnt =0;
    private Boolean attnBefore= false;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private int point=0;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
  
    private int level = 1;

    private LocalDateTime enrollDate;
    
    private int status =0;




    
    public User update(String name, String imgUrl) {
    	this.name = name;
    	this.imageUrl = imgUrl;
    	return this;
    }


    //출석 관련 Method;

    public User attendDone(Boolean isAttend){
        this.isAttend = isAttend;
        return this;
    }

    public User addAttndCnt(){
        this.attendCnt = this.attendCnt+1;
        return this;
    }

    public User deleteAttnCnt(){
        this.attendCnt = 1;
        return this;
    }

    public User attndBefore(Boolean check){
        this.attnBefore = check;
        return this;
    }


    @Builder
	public User(Long id, String name, @Email String email, String imageUrl,
                Boolean emailVerified, Boolean isAttend, Boolean attnBefore, int attendCnt,
                String password, @NotNull AuthProvider provider, String providerId,
                int point, Role role, int level, LocalDateTime enrollDate, int status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.emailVerified = emailVerified;
		this.password = password;
		this.provider = provider;
		this.providerId = providerId;
		this.point = point;
		this.role = role;
		this.level = level;
		this.isAttend = isAttend;
		this.attendCnt = attendCnt;
		this.enrollDate = enrollDate;
		this.status = status;
	}
    
}
