package com.erbf.bugsLife.attendance.service.logic;

import com.erbf.bugsLife.attendance.domain.Attendance;
import com.erbf.bugsLife.attendance.repositoty.AttendanceRepository;
import com.erbf.bugsLife.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {


    @Autowired
    AttendanceRepository repo;

    @Override
    public List<Attendance> getAttendList() {
        return repo.findAll();
    }

    @Override
    public List<Attendance> getAttendListByUser(Long uid) {
        return repo.findByUid(uid);
    }

    @Override
    public void createAttendance(Attendance attendance) {
        repo.save(attendance);
    }
}
