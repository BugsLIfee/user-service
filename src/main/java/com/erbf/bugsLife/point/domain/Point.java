package com.erbf.bugsLife.point.domain;

import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.point.application.web.PointDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
        name = "POINT_SEQ_GEN",
        sequenceName = "point_seq",
        initialValue = 1,
        allocationSize = 2
)
public class Point {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "POINT_SEQ_GEN"
    )
    private Long id;

    private int amount;
    private String registDate;
    private String detail;
    private boolean gain;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user) {
        this.user = user;
        this.
        user.getPointList().add(this);
    }

    public PointDto toDto() {
        return PointDto.builder()
                .id(this.id)
                .amount(this.amount)
                .registDate(this.registDate)
                .detail(this.detail)
                .gain(this.gain)
                .build();
    }
}
