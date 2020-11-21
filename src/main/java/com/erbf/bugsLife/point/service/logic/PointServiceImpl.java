package com.erbf.bugsLife.point.service.logic;

import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.service.UserService;
import com.erbf.bugsLife.point.application.web.PointDto;
import com.erbf.bugsLife.point.domain.Point;
import com.erbf.bugsLife.point.repository.PointRepository;
import com.erbf.bugsLife.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointRepository pointRepo;

    @Autowired
    UserService userService;

    @Override
    public void pointCreate(PointDto pointDto) {
        Point point = Point.builder()
                .amount(pointDto.getAmount())
                .registDate(getDate())
                .detail(pointDto.getDetail())
                .gain((pointDto.getAmount() > 0) ? true : false)
                .build();

        User user = userService.selectUser(pointDto.getUserId());
        point.addUser(user);
        user.setPoint(user.getPoint() + point.getAmount());

        pointRepo.save(point);
    }

    public List<PointDto> pointList() {
        List<Point> points = pointRepo.findAll();
        List<PointDto> pointDtos = new ArrayList<>();
        pointDtos = points.stream().map(Point::toDto).collect(Collectors.toList());
        return pointDtos;
    }

    private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return transFormat.format(date);
    }

}
