package com.erbf.bugsLife.attendance.service;

import com.erbf.bugsLife.attendance.domain.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceService {

    public List<Attendance> getAttendList();
    public List<Attendance> getAttendListByUser(Long uid);
    public void createAttendance(Attendance attendance);

}
