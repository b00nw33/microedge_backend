package com.microedge.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course", schema = "microedge", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // ← Critical!
    @JoinColumn(name = "trainer_id", nullable = false)
    private User trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    private Integer duration; // minutes?

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "course")
    private java.util.List<Module> modules;

    @OneToMany(mappedBy = "course")
    private java.util.List<TraineeCourse> traineeEnrollments;

    @OneToMany(mappedBy = "course")
    private java.util.List<TrainerCourse> trainerAssignments;

    public enum Level {
        BEGINNER, INTERMEDIATE, ADVANCED
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}