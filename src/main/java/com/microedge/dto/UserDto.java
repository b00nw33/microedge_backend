// src/main/java/com/microedge/dto/UserDto.java
package com.microedge.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    // ❌ No password in DTO!
}