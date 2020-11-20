package com.erbf.bugsLife.attendance.application.web;

import com.erbf.bugsLife.attendance.application.web.dto.AttendanceDto;
import com.erbf.bugsLife.attendance.domain.Attendance;
import com.erbf.bugsLife.attendance.repositoty.AttendanceRepository;
import com.erbf.bugsLife.attendance.service.AttendanceService;
import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableScheduling
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/attendance/")
public class AttendanceController {

    @Autowired
    AttendanceService service;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AttendanceRepository repo;

    @GetMapping("/{uid}")
    public List<AttendanceDto> getAttendListByUser(@PathVariable Long uid){
        List<Attendance> attendList = service.getAttendListByUser(uid);
        return attendList.stream().map(attend-> new AttendanceDto(attend)).collect(Collectors.toList());
    }

    @GetMapping()
    public List<AttendanceDto> getAllAttendList(){
        List<Attendance> attendanceList = service.getAttendList();
        return attendanceList.stream().map(attend-> new AttendanceDto(attend)).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<?> attend(@RequestBody AttendanceDto attendanceDto){
        //출석 정보 추가
        service.createAttendance(attendanceDto.toEntity(attendanceDto));

        //전에도 출석했었나 체크하고, 어제도 출석했으면 attndCnt++ / 아니면 1로 세팅;
        Optional<User> user = userRepo.findById(attendanceDto.getUid());
        if (user.get().getAttnBefore())
            user.get().addAttndCnt();
        else
            user.get().deleteAttnCnt();

        Gson json = new Gson();
        System.out.println("printout User info========" + json.toJson(user));

        //출석한 유저 찾아서 isAttend == true로 바꿔주기
        userRepo.save(user.get().attendDone(true));

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    ///00시 00분 00초에 유저정보 초기화;
    @Scheduled(cron ="0 0 0 * * *", zone = "Asia/Seoul")
    public void clearAttendance(){
        System.out.println("The CurrentTime is" + LocalDateTime.now());

        List<User> users = userRepo.findAll();

        for(int i =0; i<users.size(); i++){
            System.out.println("유저 정보 초기화~!");
            User user = users.get(i);

            //만약 이전에 출석체크 되어있으면 cnt++ /  아니면 0으로
            if(user.getIsAttend() == true )
                user.attndBefore(true);
            else
                user.attndBefore(false);

            // 출석상태 다시 false로
            user.attendDone(false);
            userRepo.save(user);
        }

    }




}
