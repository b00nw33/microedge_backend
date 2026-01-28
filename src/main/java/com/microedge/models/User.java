package com.microedge.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", schema = "microedge")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

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

    public enum Role {
        TRAINER, TRAINEE
    }
}