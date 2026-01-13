package com.agile_project_management.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserStory> userStories = new ArrayList<>();

    @OneToMany(mappedBy = "productBacklog", cascade = CascadeType.ALL)
    private List<Epic> epics = new ArrayList<>();
}