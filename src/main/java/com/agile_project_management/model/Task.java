package com.agile_project_management.model;

import com.agile_project_management.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_story_id", nullable = false)
    private UserStory userStory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignee;
}