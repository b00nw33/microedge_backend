// src/main/java/com/microedge/controllers/ModuleController.java
package com.microedge.controllers;

import com.microedge.dto.CreateModuleDto;
import com.microedge.dto.ModuleDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/modules")
@CrossOrigin("*")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping
    public ResponseEntity<List<ModuleDto>> getAllModules(@PathVariable Integer courseId) {
        try {
            return ResponseEntity.ok(moduleService.findAllByCourse(courseId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ModuleDto> createModule(
            @PathVariable Integer courseId,
            @RequestBody CreateModuleDto dto) {
        try {
            ModuleDto saved = moduleService.create(courseId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ModuleDto> getModule(
            @PathVariable Integer courseId,
            @PathVariable Integer moduleId) {
        try {
            ModuleDto module = moduleService.findById(moduleId);
            // Optional: Verify module belongs to course
            if (!module.getCourseId().equals(courseId)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(module);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<ModuleDto> updateModule(
            @PathVariable Integer courseId,
            @PathVariable Integer moduleId,
            @RequestBody CreateModuleDto dto) {
        try {
            ModuleDto updated = moduleService.update(moduleId, dto);
            if (!updated.getCourseId().equals(courseId)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<Void> deleteModule(
            @PathVariable Integer courseId,
            @PathVariable Integer moduleId) {
        try {
            ModuleDto module = moduleService.findById(moduleId);
            if (!module.getCourseId().equals(courseId)) {
                return ResponseEntity.notFound().build();
            }
            moduleService.deleteById(moduleId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}