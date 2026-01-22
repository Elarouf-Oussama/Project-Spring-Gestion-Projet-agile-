package com.agile_project_management.model;

import com.agile_project_management.model.enums.Priority;
import com.agile_project_management.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String acceptanceCriteria;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Integer storyPoints;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // Relations
    @ManyToOne
    @JoinColumn(name = "product_backlog_id")
    private ProductBacklog productBacklog;

    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

    @ManyToOne
    @JoinColumn(name = "sprint_backlog_id")
    private SprintBacklog sprintBacklog;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}