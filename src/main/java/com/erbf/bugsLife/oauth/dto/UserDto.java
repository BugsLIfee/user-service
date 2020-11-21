package com.erbf.bugsLife.oauth.dto;

import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.payment.application.web.PaymentDto;
import com.erbf.bugsLife.point.application.web.PointDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private boolean emailVerified;
    private boolean isAttend;
    private int attendCnt;
    private boolean attenBefore;
    private String password;
    private int point;
    private String role;
    private int level;
    private LocalDateTime enrollDate;
    private int status;
    private List<PointDto> pointList = new ArrayList<>();
    private List<PaymentDto> paymentList = new ArrayList<>();

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .imageUrl(this.imageUrl)
                .emailVerified(this.emailVerified)
                .build();
    }
}
