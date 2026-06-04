package com.musiclibrary.adminservice.controller;

import com.musiclibrary.adminservice.dto.*;
import com.musiclibrary.adminservice.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin API", description = "Admin login and user management")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    @Operation(summary = "Admin login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody AdminLoginDTO dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }

    @PostMapping("/logout")
    @Operation(summary = "Admin logout")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("message", "Admin logged out successfully"));
    }

    @GetMapping("/profile/{id}")
    @Operation(summary = "Get admin profile", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                       @RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(adminService.updateUser(id, dto));
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    @PatchMapping("/users/{id}/disable")
    @Operation(summary = "Disable user (restrict song visibility for user)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> disableUser(@PathVariable Long id) {
        adminService.disableUser(id);
        return ResponseEntity.ok(Map.of("message", "User disabled successfully"));
    }

    @PatchMapping("/users/{id}/enable")
    @Operation(summary = "Enable user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> enableUser(@PathVariable Long id) {
        adminService.enableUser(id);
        return ResponseEntity.ok(Map.of("message", "User enabled successfully"));
    }
}
