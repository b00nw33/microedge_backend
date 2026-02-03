// src/main/java/com/microedge/controllers/ModuleProgressController.java
package com.microedge.controllers;

import com.microedge.dto.CompleteModuleDto;
import com.microedge.dto.ModuleProgressDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.services.ModuleProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
@CrossOrigin("*")
public class ModuleProgressController {

    @Autowired
    private ModuleProgressService progressService;

    @GetMapping("/trainee/{traineeId}")
    public ResponseEntity<List<ModuleProgressDto>> getProgressByTrainee(@PathVariable Integer traineeId) {
        try {
            return ResponseEntity.ok(progressService.getProgressByTrainee(traineeId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/trainee/{traineeId}/course/{courseId}")
    public ResponseEntity<List<ModuleProgressDto>> getProgressByTraineeAndCourse(
            @PathVariable Integer traineeId,
            @PathVariable Integer courseId) {
        try {
            return ResponseEntity.ok(progressService.getProgressByTraineeAndCourse(traineeId, courseId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/trainee/{traineeId}/complete")
    public ResponseEntity<ModuleProgressDto> completeModule(
            @PathVariable Integer traineeId,
            @RequestBody CompleteModuleDto dto) {
        try {
            ModuleProgressDto result = progressService.completeModule(traineeId, dto);
            return ResponseEntity.ok(result);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}