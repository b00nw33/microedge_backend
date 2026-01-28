// src/main/java/com/microedge/dto/CreateCourseDto.java
package com.microedge.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseDto {

    @NotNull(message = "Trainer ID is required")
    private Integer trainerId;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be under 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    @NotBlank(message = "Level is required")
    @Pattern(regexp = "BEGINNER|INTERMEDIATE|ADVANCED", 
             message = "Level must be BEGINNER, INTERMEDIATE, or ADVANCED")
    private String level;

    private String imageUrl;
}