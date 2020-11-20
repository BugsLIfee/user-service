package com.erbf.bugsLife.attendance.application.web.dto;

import com.erbf.bugsLife.attendance.domain.Attendance;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceDto {

    private Long id;
    private Long uid;
    private String attendDate;

    public AttendanceDto(Attendance attendance){
        this.id = attendance.getId();
        this.uid=attendance.getUid();
        this.attendDate = attendance.getAttendDate();
    }

    public Attendance toEntity(AttendanceDto attendanceDto){
        Gson json = new Gson();
        System.out.println(json.toJson(attendanceDto));

        return Attendance.builder()
                .id(this.id)
                .uid(this.uid)
                .attendDate(this.attendDate)
                .build();
    }

}
