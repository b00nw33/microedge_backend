// src/main/java/com/microedge/repositories/ModuleProgressRepository.java
package com.microedge.repositories;

import com.microedge.models.ModuleProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, Integer> {
    List<ModuleProgress> findByTraineeId(Integer traineeId);

    List<ModuleProgress> findByModuleId(Integer moduleId);

    // Find progress for a specific trainee in a specific course
    @Query("SELECT mp FROM ModuleProgress mp WHERE mp.trainee.id = :traineeId AND mp.module.course.id = :courseId")
    List<ModuleProgress> findByTraineeAndCourse(
            @Param("traineeId") Integer traineeId,
            @Param("courseId") Integer courseId);

    // Check if a specific module is completed
    boolean existsByTraineeIdAndModuleIdAndCompleted(
            Integer traineeId,
            Integer moduleId,
            Boolean completed);

    Optional<ModuleProgress> findByTraineeIdAndModuleId(Integer traineeId, Integer moduleId);
}