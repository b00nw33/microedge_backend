// src/main/java/com/microedge/controllers/EnrollmentController.java
package com.microedge.controllers;

import com.microedge.dto.CreateEnrollmentDto;
import com.microedge.dto.EnrollmentDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody CreateEnrollmentDto dto) {
        try {
            EnrollmentDto saved = enrollmentService.createEnrollment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/trainee/{traineeId}")
    public ResponseEntity<List<EnrollmentDto>> getEnrollmentsByTrainee(@PathVariable Integer traineeId) {
        try {
            return ResponseEntity.ok(enrollmentService.findEnrollmentsByTrainee(traineeId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDto>> getEnrollmentsByCourse(@PathVariable Integer courseId) {
        try {
            return ResponseEntity.ok(enrollmentService.findEnrollmentsByCourse(courseId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{enrollmentId}/complete")
    public ResponseEntity<?> markEnrollmentComplete(@PathVariable Integer enrollmentId) {
        try {
            EnrollmentDto updated = enrollmentService.markAsCompleted(enrollmentId);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}