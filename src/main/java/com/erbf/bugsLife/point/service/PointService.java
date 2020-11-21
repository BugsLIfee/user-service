package com.erbf.bugsLife.point.service;

import com.erbf.bugsLife.point.application.web.PointDto;

import java.util.List;

public interface PointService {
    public abstract void pointCreate(PointDto pointDto);
    public abstract List<PointDto> pointList();
}
