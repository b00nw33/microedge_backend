package com.microedge.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trainee_course", schema = "microedge",
       uniqueConstraints = @UniqueConstraint(columnNames = {"users_id", "course_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User trainee;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enroll_date", nullable = false)
    private LocalDateTime enrollDate = LocalDateTime.now();

    @Column(name = "complete_date", nullable = false)
    private LocalDateTime completeDate = LocalDateTime.now();

    @Column(name = "complete_status")
    private Boolean completeStatus = false;
}