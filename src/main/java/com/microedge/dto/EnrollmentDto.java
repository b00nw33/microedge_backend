// src/main/java/com/microedge/dto/EnrollmentDto.java
package com.microedge.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
    private Integer id;
    private Integer traineeId;
    private String traineeName;
    private Integer courseId;
    private String courseTitle;
    private LocalDateTime enrollDate;
    private LocalDateTime completeDate;
    private Boolean completeStatus;
}