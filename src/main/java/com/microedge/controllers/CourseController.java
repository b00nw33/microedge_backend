// src/main/java/com/microedge/controllers/CourseController.java
package com.microedge.controllers;

import com.microedge.dto.CourseDto;
import com.microedge.dto.CreateCourseDto;
import com.microedge.dto.UpdateCourseDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.services.CourseService;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("id", "title", "createdAt", "updatedAt",
            "duration");

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody CreateCourseDto dto) {
        // No need for try-catch - validation errors are handled globally
        CourseDto saved = courseService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCourseDto dto) {
        try {
            CourseDto updated = courseService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        try {
            courseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<CourseDto>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Integer trainerId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String search) {

        // Validate sort parameters
        Pageable pageable = buildPageable(sortBy, sortDir, page, size);

        Page<CourseDto> courses;

        if (search != null && !search.trim().isEmpty()) {
            // 🔍 SEARCH MODE
            if (trainerId != null && categoryId != null) {
                courses = courseService.searchByTrainerCategoryAndKeyword(trainerId, categoryId, search, pageable);
            } else if (trainerId != null) {
                courses = courseService.searchByTrainerAndKeyword(trainerId, search, pageable);
            } else if (categoryId != null) {
                courses = courseService.searchByCategoryAndKeyword(categoryId, search, pageable);
            } else {
                courses = courseService.searchByKeyword(search, pageable);
            }
        } else {
            // 📋 FILTER MODE (no search)
            if (trainerId != null && categoryId != null) {
                courses = courseService.findByTrainerAndCategory(trainerId, categoryId, pageable);
            } else if (trainerId != null) {
                courses = courseService.findByTrainer(trainerId, pageable);
            } else if (categoryId != null) {
                courses = courseService.findByCategory(categoryId, pageable);
            } else {
                courses = courseService.findAll(pageable);
            }
        }

        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(courseService.findById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // In CourseController.java

    @GetMapping("/by-trainer/{trainerId}")
    public ResponseEntity<Page<CourseDto>> getCoursesByTrainer(
            @PathVariable Integer trainerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = buildPageable(sortBy, sortDir, page, size);
        Page<CourseDto> courses = courseService.findByTrainer(trainerId, pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<Page<CourseDto>> getCoursesByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = buildPageable(sortBy, sortDir, page, size);
        Page<CourseDto> courses = courseService.findByCategory(categoryId, pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/by-trainer-category")
    public ResponseEntity<Page<CourseDto>> getCoursesByTrainerAndCategory(
            @RequestParam Integer trainerId,
            @RequestParam Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Pageable pageable = buildPageable(sortBy, sortDir, page, size);
        Page<CourseDto> courses = courseService.findByTrainerAndCategory(trainerId, categoryId, pageable);
        return ResponseEntity.ok(courses);
    }

    private Pageable buildPageable(String sortBy, String sortDir, int page, int size) {
        // Validate sort field
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        // Handle sort direction
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }
}