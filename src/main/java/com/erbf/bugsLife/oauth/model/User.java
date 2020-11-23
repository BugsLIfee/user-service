package com.erbf.bugsLife.oauth.model;
import com.erbf.bugsLife.oauth.dto.UserDto;
import com.erbf.bugsLife.payment.domain.Payment;
import com.erbf.bugsLife.point.domain.Point;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
@Table(name = "oauth_user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email")
})
@Entity
@SequenceGenerator(
        name="USER_SEQ_GEN",
        sequenceName = "user_seq",
        initialValue = 1,
        allocationSize = 2
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_SEQ_GEN"
    )
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
    private Boolean attenBefore= false;

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
    
    private int status = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Point> pointList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Payment> paymentList = new ArrayList<>();

    
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

    public User attenBefore(Boolean check){
        this.attenBefore = check;
        return this;
    }


    @Builder
	public User(Long id, String name, @Email String email, String imageUrl,
                Boolean emailVerified, Boolean isAttend, Boolean attenBefore, int attendCnt,
                String password, @NotNull AuthProvider provider, String providerId,
                int point, Role role, int level, LocalDateTime enrollDate, int status,
                List<Point> pointList, List<Payment> paymentList) {
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
        this.attenBefore= attenBefore;
		this.enrollDate = enrollDate;
		this.status = status;
		this.paymentList = paymentList;
		this.pointList = pointList;
	}

	public UserDto toDto() {
        return UserDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .imageUrl(this.imageUrl)
                .emailVerified(this.emailVerified)
                .point(this.point)
                .level(this.level)
                .role(this.role.getName())
                .isAttend(this.isAttend)
                .attendCnt(this.attendCnt)
                .attenBefore(this.attenBefore)
                .enrollDate(this.enrollDate)
                .status(this.status)
                .pointList(this.pointList.stream().map(Point::toDto).collect(Collectors.toList()))
                .paymentList(this.paymentList.stream().map(Payment::toDto).collect(Collectors.toList()))
                .build();
    }
}
