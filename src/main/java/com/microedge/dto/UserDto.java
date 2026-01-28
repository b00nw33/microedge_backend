// src/main/java/com/microedge/dto/UserDto.java
package com.microedge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microedge.models.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder                                                    // Lombok builder pattern, resolving the "Constructor Nightmare"
public class UserDto {

    private Integer id;
    private User.Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String refreshToken;
    private Long expirationTime;
    private String message;

    @ToString.Exclude                                       // Precision (field level): Prevents passwords from being printed
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Hide password in responses
    private String password;

    /*
    @Builder eradicates overloading constructors
    Implements a Builder Pattern to chain syntax at instantiation
    Example:
    UserDto dto = UserDto.builder()
            .id(1)
            .role(EnumRole.ADMIN)
            .firstName("John")
            .lastName("Doe")
            .email("john@email.com")
            .password("abc123")
            .build();
    */
}