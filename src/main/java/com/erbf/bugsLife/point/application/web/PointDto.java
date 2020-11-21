package com.erbf.bugsLife.point.application.web;


import com.erbf.bugsLife.point.domain.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PointDto {

    private Long id;
    private int amount;
    private String registDate;
    private String detail;
    private Long userId;
    private boolean gain;


    public Point toEntity() {
        return Point.builder()
                .amount(this.amount)
                .detail(this.detail)
                .gain(this.gain)
                .build();
    }
}
