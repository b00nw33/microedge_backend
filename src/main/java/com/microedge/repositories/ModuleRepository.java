// src/main/java/com/microedge/repositories/ModuleRepository.java
package com.microedge.repositories;

import com.microedge.models.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    Page<Module> findByCourseId(Integer courseId, Pageable pageable);
    long countByCourseId(Integer courseId);
}