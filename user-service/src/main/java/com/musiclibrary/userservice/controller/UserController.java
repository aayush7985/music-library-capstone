package com.musiclibrary.userservice.controller;

import com.musiclibrary.userservice.dto.*;
import com.musiclibrary.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "User registration, login, and management")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user and get JWT token")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout (client-side token invalidation)")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    @Operation(summary = "Get all users (admin use)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user profile", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                       @Valid @RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Disable user account", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> disableUser(@PathVariable Long id) {
        userService.disableUser(id);
        return ResponseEntity.ok(Map.of("message", "User disabled successfully"));
    }

    @PatchMapping("/{id}/enable")
    @Operation(summary = "Enable user account", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> enableUser(@PathVariable Long id) {
        userService.enableUser(id);
        return ResponseEntity.ok(Map.of("message", "User enabled successfully"));
    }
}
