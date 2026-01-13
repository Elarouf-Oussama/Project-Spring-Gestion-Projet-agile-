package com.agile_project_management.service;

import com.agile_project_management.model.User;
import java.util.Optional;

public interface IUserService {
    // Inscription d'un nouvel utilisateur
    User registerUser(User user);

    // Trouver un utilisateur
    Optional<User> findByUsername(String username);

    // Vérifier si un email existe déjà
    Boolean existsByEmail(String email);
}