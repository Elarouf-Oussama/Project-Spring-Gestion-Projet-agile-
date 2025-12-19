package com.agile_project_management.controller;

import com.agile_project_management.dto.request.LoginRequest;
import com.agile_project_management.model.User;
import com.agile_project_management.model.enums.RoleType;
import com.agile_project_management.repository.UserRepository;
import com.agile_project_management.security.jwt.JwtUtils;
import com.agile_project_management.security.services.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private JwtUtils jwtUtils;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldLoginSuccessfully() throws Exception {
        // 1. Préparer la requête
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password123");

        // 2. Mocker l'authentification
        Authentication authentication = Mockito.mock(Authentication.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(1L, "testuser", "test@test.com", "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);
        Mockito.when(jwtUtils.generateJwtToken(authentication)).thenReturn("fake-jwt-token");

        // 3. Exécuter
        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk()); // Doit retourner 200 OK
    }

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        // 1. Préparer l'utilisateur
        User user = new User();
        user.setUsername("newuser");
        user.setEmail("new@test.com");
        user.setPassword("password");
        user.setRole(RoleType.DEVELOPPER);

        // 2. Mocker le repository (username et email n'existent pas encore)
        Mockito.when(userRepository.existsByUsername("newuser")).thenReturn(false);
        Mockito.when(userRepository.existsByEmail("new@test.com")).thenReturn(false);

        // 3. Exécuter
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk()); // Doit retourner 200 OK
    }
}