// src/main/java/com/microedge/services/ModuleProgressService.java
package com.microedge.services;

import com.microedge.dto.CompleteModuleDto;
import com.microedge.dto.ModuleProgressDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.models.ModuleProgress;
import com.microedge.models.User;
import com.microedge.models.Module;
import com.microedge.repositories.ModuleProgressRepository;
import com.microedge.repositories.UserRepository;
import com.microedge.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// ModuleProgressService.java
@Service
@Transactional
public class ModuleProgressService {

    @Autowired
    private ModuleProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<ModuleProgressDto> getProgressByTrainee(Integer traineeId) {
        validateUserExists(traineeId);
        return progressRepository.findByTraineeId(traineeId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ModuleProgressDto> getProgressByTraineeAndCourse(Integer traineeId, Integer courseId) {
        validateUserExists(traineeId);
        return progressRepository.findByTraineeAndCourse(traineeId, courseId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ✅ THIS METHOD IS ALREADY CORRECT - NO CHANGES NEEDED
    public ModuleProgressDto completeModule(Integer traineeId, CompleteModuleDto dto) {
        // Validate trainee exists and is TRAINEE
        User trainee = userRepository.findById(traineeId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainee not found: " + traineeId));
        if (trainee.getRole() != User.Role.TRAINEE) {
            throw new IllegalArgumentException("User is not a trainee");
        }

        // Validate module exists
        Module module = moduleRepository.findById(dto.getModuleId())
                .orElseThrow(() -> new ResourceNotFoundException("Module not found: " + dto.getModuleId()));

        // Get or create progress record
        ModuleProgress progress = progressRepository.findByTraineeIdAndModuleId(traineeId, dto.getModuleId())
                .orElseGet(() -> {
                    ModuleProgress newProgress = new ModuleProgress();
                    newProgress.setTrainee(trainee);
                    newProgress.setModule(module);
                    return newProgress;
                });

        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());

        ModuleProgress saved = progressRepository.save(progress);
        return toDto(saved);
    }

    private ModuleProgressDto toDto(ModuleProgress progress) {
        ModuleProgressDto dto = new ModuleProgressDto();
        dto.setId(progress.getId());
        dto.setTraineeId(progress.getTrainee().getId());
        dto.setTraineeName(
            progress.getTrainee().getFirstName() + " " + 
            progress.getTrainee().getLastName()
        );
        dto.setModuleId(progress.getModule().getId());
        dto.setModuleName(progress.getModule().getTitle());
        dto.setCourseId(progress.getModule().getCourse().getId());
        dto.setCourseTitle(progress.getModule().getCourse().getTitle());
        dto.setCompleted(progress.getCompleted());
        dto.setCompletedAt(progress.getCompletedAt());
        return dto;
    }

    private void validateUserExists(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found: " + userId);
        }
    }
}