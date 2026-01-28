// src/main/java/com/microedge/models/ModuleProgress.java
package com.microedge.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "module_progress", schema = "microedge",
       uniqueConstraints = @UniqueConstraint(columnNames = {"trainee_id", "module_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trainee_id", nullable = false)
    private User trainee;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    private Boolean completed = false;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}