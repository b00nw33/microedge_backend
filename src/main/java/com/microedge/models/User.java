package com.microedge.models;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "users", schema = "microedge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.TRAINEE;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    @ToString.Exclude                                      // Precision (field level): Prevents passwords from being printed
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Hide password in responses
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "trainer")
    @JsonIgnore
    private List<Course> coursesAsTrainer;

    @OneToMany(mappedBy = "trainee")
    @JsonIgnore
    private List<TraineeCourse> enrolledCourses;

    @OneToMany(mappedBy = "trainerUser")
    @JsonIgnore
    private List<TrainerCourse> publishedCourses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum Role {
        TRAINER, TRAINEE
    }

    /**
     *********************************************************************
     @Builder eradicates overloading constructors
     - Implement a Builder Pattern to chain syntax at instantiation
     - The constructor is created with only the fields for the builder
     - Note: Place @Builder here instead of at the top of the class
      *********************************************************************
     Example of using the builder pattern:
     User user = User.builder()
     .userName("John")
     .email("john@email.com")
     .role(EnumRole.ADMIN)
     .build();
     *********************************************************************
     */
    @Builder
    public User(String firstName,
                String lastName,
                String email,
                String password,
                Role role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = (role == null) ? Role.TRAINEE : role;
    }

}