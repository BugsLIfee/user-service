package com.erbf.bugsLife.mypage.application.web;

import com.erbf.bugsLife.mypage.application.web.dto.UserDto;
import com.erbf.bugsLife.mypage.domain.user.User;
import com.erbf.bugsLife.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("")
public class MypageController {

    @Autowired
    MypageService service;

    @GetMapping("/api/hello")
    public String helloWorld(){
        return "Hello";
    }


    @GetMapping("/api/mypage/{uid}")
    public UserDto findUser(@PathVariable Long uid){
        return service.userDetail(uid).toDto();
    }

    @GetMapping("/api/mypage/")
    public List<UserDto> userList (){
        return User.toDtoList(service.userList());
    }

    @PutMapping()
    public void userUpdate(@RequestBody UserDto user){
        service.userUpdate(user.toEntitiy());
    }

    @PostMapping("/api/signup/")
    public void userCreate(@RequestBody UserDto user){
        System.out.println(user);
        service.userCreate(user.toEntitiy());
    }



}
