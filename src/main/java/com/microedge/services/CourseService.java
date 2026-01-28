// src/main/java/com/microedge/services/CourseService.java
package com.microedge.services;

import com.microedge.dto.CourseDto;
import com.microedge.dto.CreateCourseDto;
import com.microedge.dto.UpdateCourseDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.models.Course;
import com.microedge.models.User;
import com.microedge.models.Category;
import com.microedge.repositories.CourseRepository;
import com.microedge.repositories.UserRepository;
import com.microedge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CourseDto findById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));
        return toDto(course);
    }

    public CourseDto create(CreateCourseDto dto) {
        // Validate trainer exists and is TRAINER
        User trainer = userRepository.findById(dto.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found: " + dto.getTrainerId()));
        if (trainer.getRole() != User.Role.TRAINER) {
            throw new IllegalArgumentException("User is not a trainer");
        }

        // Validate category exists
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + dto.getCategoryId()));

        // Build course
        Course course = new Course();
        course.setTrainer(trainer);
        course.setCategory(category);
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setLevel(Course.Level.valueOf(dto.getLevel().toUpperCase()));
        course.setImageUrl(dto.getImageUrl());

        Course saved = courseRepository.save(course);
        return toDto(saved);
    }

    public void deleteById(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found: " + id);
        }
        courseRepository.deleteById(id);
    }

    // Helper: Convert entity → DTO
    private CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTrainerId(course.getTrainer().getId());
        dto.setTrainerName(course.getTrainer().getFirstName() + " " + course.getTrainer().getLastName());
        dto.setCategoryId(course.getCategory().getId());
        dto.setCategoryName(course.getCategory().getName());
        dto.setCreatedAt(course.getCreatedAt());
        dto.setUpdatedAt(course.getUpdatedAt());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());
        dto.setLevel(course.getLevel().name());
        dto.setImageUrl(course.getImageUrl());
        return dto;
    }

    public CourseDto update(Integer id, UpdateCourseDto dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setLevel(Course.Level.valueOf(dto.getLevel().toUpperCase()));
        course.setImageUrl(dto.getImageUrl());

        Course updated = courseRepository.save(course);
        return toDto(updated);
    }

    // For pagination
    public Page<CourseDto> findAll(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(this::toDto);
    }

    // New method for filtered queries
    public Page<CourseDto> findByFilters(
            Integer trainerId,
            Integer categoryId,
            Pageable pageable) {

        Page<Course> courses;

        if (trainerId != null && categoryId != null) {
            courses = courseRepository.findByTrainerIdAndCategoryId(trainerId, categoryId, pageable);
        } else if (trainerId != null) {
            courses = courseRepository.findByTrainerId(trainerId, pageable);
        } else {
            courses = courseRepository.findByCategoryId(categoryId, pageable);
        }

        return courses.map(this::toDto);
    }

    public Page<CourseDto> findByTrainer(Integer trainerId, Pageable pageable) {
        if (!userRepository.existsById(trainerId)) {
            throw new ResourceNotFoundException("Trainer not found: " + trainerId);
        }
        return courseRepository.findByTrainerId(trainerId, pageable)
                .map(this::toDto);
    }

    public Page<CourseDto> findByCategory(Integer categoryId, Pageable pageable) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found: " + categoryId);
        }
        return courseRepository.findByCategoryId(categoryId, pageable)
                .map(this::toDto);
    }

    public Page<CourseDto> findByTrainerAndCategory(
            Integer trainerId,
            Integer categoryId,
            Pageable pageable) {

        if (!userRepository.existsById(trainerId)) {
            throw new ResourceNotFoundException("Trainer not found: " + trainerId);
        }
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found: " + categoryId);
        }

        return courseRepository.findByTrainerIdAndCategoryId(trainerId, categoryId, pageable)
                .map(this::toDto);
    }

    // 🔍 Search only
    public Page<CourseDto> searchByKeyword(String keyword, Pageable pageable) {
        return courseRepository.findByKeyword(keyword, pageable)
                .map(this::toDto);
    }

    // 🔍 Search + Trainer
    public Page<CourseDto> searchByTrainerAndKeyword(Integer trainerId, String keyword, Pageable pageable) {
        validateTrainerExists(trainerId);
        return courseRepository.findByTrainerIdAndKeyword(trainerId, keyword, pageable)
                .map(this::toDto);
    }

    // 🔍 Search + Category
    public Page<CourseDto> searchByCategoryAndKeyword(Integer categoryId, String keyword, Pageable pageable) {
        validateCategoryExists(categoryId);
        return courseRepository.findByCategoryIdAndKeyword(categoryId, keyword, pageable)
                .map(this::toDto);
    }

    // 🔍 Search + Trainer + Category
    public Page<CourseDto> searchByTrainerCategoryAndKeyword(
            Integer trainerId,
            Integer categoryId,
            String keyword,
            Pageable pageable) {

        validateTrainerExists(trainerId);
        validateCategoryExists(categoryId);
        return courseRepository.findByTrainerIdAndCategoryIdAndKeyword(trainerId, categoryId, keyword, pageable)
                .map(this::toDto);
    }

    // Helper validators
    private void validateTrainerExists(Integer trainerId) {
        if (!userRepository.existsById(trainerId)) {
            throw new ResourceNotFoundException("Trainer not found: " + trainerId);
        }
    }

    private void validateCategoryExists(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found: " + categoryId);
        }
    }

    
}