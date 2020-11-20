package com.erbf.bugsLife.attendance.repositoty;

import com.erbf.bugsLife.attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    public List<Attendance> findByUid(Long uid);
}
