// src/main/java/com/microedge/dto/CreateModuleDto.java
package com.microedge.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModuleDto {
    @NotBlank(message = "Title is required")
    private String title;

    private String contentText;
    private String imageUrl;
    private String videoUrl;
}