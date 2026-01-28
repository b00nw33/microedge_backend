// src/main/java/com/microedge/dto/CompleteModuleDto.java
package com.microedge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteModuleDto {
    @NotNull(message = "Module ID is required")
    private Integer moduleId;
}