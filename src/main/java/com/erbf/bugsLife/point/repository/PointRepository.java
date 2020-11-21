package com.erbf.bugsLife.point.repository;

import com.erbf.bugsLife.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {

}
