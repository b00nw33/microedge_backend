// src/main/java/com/microedge/dto/CreateEnrollmentDto.java
package com.microedge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEnrollmentDto {
    @NotNull(message = "Trainee ID is required")
    private Integer traineeId;

    @NotNull(message = "Course ID is required")
    private Integer courseId;
}