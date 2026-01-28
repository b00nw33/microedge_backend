// src/main/java/com/microedge/dto/CourseDto.java
package com.microedge.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Integer id;
    private Integer trainerId;
    private String trainerName; // Denormalized for UX
    private Integer categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private String description;
    private Integer duration; // in minutes
    private String level; // "BEGINNER", "INTERMEDIATE", "ADVANCED"
    private String imageUrl;
}