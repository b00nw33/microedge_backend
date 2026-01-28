package com.microedge.repositories;

import com.microedge.models.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Find by trainer only
    Page<Course> findByTrainerId(Integer trainerId, Pageable pageable);

    // Find by category only
    Page<Course> findByCategoryId(Integer categoryId, Pageable pageable);

    // Find by both
    Page<Course> findByTrainerIdAndCategoryId(
            Integer trainerId,
            Integer categoryId,
            Pageable pageable);

    // 🔍 NEW: Text search
    @Query("SELECT c FROM Course c WHERE " +
            "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Course> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // 🔍 NEW: Search + Trainer
    @Query("SELECT c FROM Course c WHERE c.trainer.id = :trainerId AND (" +
            "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Course> findByTrainerIdAndKeyword(
            @Param("trainerId") Integer trainerId,
            @Param("keyword") String keyword,
            Pageable pageable);

    // 🔍 NEW: Search + Category
    @Query("SELECT c FROM Course c WHERE c.category.id = :categoryId AND (" +
            "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Course> findByCategoryIdAndKeyword(
            @Param("categoryId") Integer categoryId,
            @Param("keyword") String keyword,
            Pageable pageable);

    // 🔍 NEW: Search + Trainer + Category
    @Query("SELECT c FROM Course c WHERE c.trainer.id = :trainerId AND c.category.id = :categoryId AND (" +
            "LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Course> findByTrainerIdAndCategoryIdAndKeyword(
            @Param("trainerId") Integer trainerId,
            @Param("categoryId") Integer categoryId,
            @Param("keyword") String keyword,
            Pageable pageable);

}