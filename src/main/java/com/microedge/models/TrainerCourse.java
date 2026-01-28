package com.microedge.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trainer_course", schema = "microedge",
       uniqueConstraints = @UniqueConstraint(columnNames = {"users_id", "course_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User trainerUser;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "publish_date")
    private LocalDateTime publishDate = LocalDateTime.now();
}