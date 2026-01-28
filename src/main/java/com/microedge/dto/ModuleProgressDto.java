// src/main/java/com/microedge/dto/ModuleProgressDto.java
package com.microedge.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleProgressDto {
    private Integer id;
    private Integer traineeId;
    private String traineeName;
    private Integer moduleId;
    private String moduleName;
    private Integer courseId;
    private String courseTitle;
    private Boolean completed;
    private LocalDateTime completedAt;
}