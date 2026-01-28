package com.microedge.repositories;

import com.microedge.models.TraineeCourse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Already exists - just add these methods
public interface TraineeCourseRepository extends JpaRepository<TraineeCourse, Integer> {
    boolean existsByTraineeIdAndCourseId(Integer traineeId, Integer courseId);
    List<TraineeCourse> findByTraineeId(Integer traineeId);
    List<TraineeCourse> findByCourseId(Integer courseId);
}