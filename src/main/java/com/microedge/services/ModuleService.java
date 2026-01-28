// src/main/java/com/microedge/services/ModuleService.java
package com.microedge.services;

import com.microedge.dto.CreateModuleDto;
import com.microedge.dto.ModuleDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.models.Course;
import com.microedge.models.Module;
import com.microedge.repositories.ModuleRepository;
import com.microedge.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<ModuleDto> findAllByCourse(Integer courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found: " + courseId);
        }
        return moduleRepository.findByCourseId(courseId, org.springframework.data.domain.Pageable.unpaged())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ModuleDto findById(Integer id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found: " + id));
        return toDto(module);
    }

    public ModuleDto create(Integer courseId, CreateModuleDto dto) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found: " + courseId);
        }

        Module module = new Module();

        // ✅ CORRECT WAY: Create course reference with ID only
        Course courseRef = new Course();
        courseRef.setId(courseId);
        module.setCourse(courseRef);

        module.setTitle(dto.getTitle());
        module.setContentText(dto.getContentText());
        module.setImageUrl(dto.getImageUrl());
        module.setVideoUrl(dto.getVideoUrl());

        Module saved = moduleRepository.save(module);
        return toDto(saved);
    }

    public ModuleDto update(Integer id, CreateModuleDto dto) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found: " + id));

        module.setTitle(dto.getTitle());
        module.setContentText(dto.getContentText());
        module.setImageUrl(dto.getImageUrl());
        module.setVideoUrl(dto.getVideoUrl());

        Module updated = moduleRepository.save(module);
        return toDto(updated);
    }

    public void deleteById(Integer id) {
        if (!moduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Module not found: " + id);
        }
        moduleRepository.deleteById(id);
    }

    private ModuleDto toDto(Module module) {
        ModuleDto dto = new ModuleDto();
        dto.setId(module.getId());
        dto.setCourseId(module.getCourse().getId());
        dto.setTitle(module.getTitle());
        dto.setContentText(module.getContentText());
        dto.setCreatedAt(module.getCreatedAt());
        dto.setUpdatedAt(module.getUpdatedAt());
        dto.setImageUrl(module.getImageUrl());
        dto.setVideoUrl(module.getVideoUrl());
        return dto;
    }
}