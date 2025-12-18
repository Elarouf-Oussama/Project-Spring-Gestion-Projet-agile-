package com.agile_project_management.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_backlog_id")
    private ProductBacklog productBacklog;

    @OneToMany(mappedBy = "epic")
    private List<UserStory> userStories = new ArrayList<>();
}