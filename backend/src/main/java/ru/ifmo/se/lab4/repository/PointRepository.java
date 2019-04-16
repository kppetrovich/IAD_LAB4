package ru.ifmo.se.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.se.lab4.domain.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
}
