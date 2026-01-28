// src/main/java/com/microedge/dto/ModuleDto.java
package com.microedge.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDto {
    private Integer id;
    private Integer courseId;
    private String title;
    private String contentText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;
    private String videoUrl;
}