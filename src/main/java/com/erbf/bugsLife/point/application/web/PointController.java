package com.erbf.bugsLife.point.application.web;

import com.erbf.bugsLife.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point/")
public class PointController {

    @Autowired
    PointService pointService;

    @PostMapping("/add/")
    public ResponseEntity<?> pointCreate(@RequestBody PointDto pointDto) {
        pointService.pointCreate(pointDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/list/")
    public List<PointDto> pointList() {
        return pointService.pointList();
    }
}
