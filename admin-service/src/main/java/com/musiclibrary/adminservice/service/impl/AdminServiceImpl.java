package com.musiclibrary.adminservice.service.impl;

import com.musiclibrary.adminservice.client.UserServiceClient;
import com.musiclibrary.adminservice.dto.*;
import com.musiclibrary.adminservice.entity.Admin;
import com.musiclibrary.adminservice.exception.AdminNotFoundException;
import com.musiclibrary.adminservice.repository.AdminRepository;
import com.musiclibrary.adminservice.service.AdminService;
import com.musiclibrary.adminservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    @Override
    public JwtResponseDTO login(AdminLoginDTO dto) {
        Admin admin = adminRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new AdminNotFoundException("Invalid credentials"));
        if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
            throw new AdminNotFoundException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(admin.getId(), admin.getEmail(), admin.getRole().name());
        return JwtResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .adminId(admin.getId())
                .email(admin.getEmail())
                .name(admin.getName())
                .role(admin.getRole().name())
                .build();
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with id: " + id));
        return AdminResponseDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .role(admin.getRole().name())
                .createdAt(admin.getCreatedAt())
                .build();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userServiceClient.getAllUsers();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userServiceClient.getUserById(id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdateUserDTO dto) {
        return userServiceClient.updateUser(id, dto);
    }

    @Override
    public void deleteUser(Long id) {
        userServiceClient.deleteUser(id);
    }

    @Override
    public void disableUser(Long id) {
        userServiceClient.disableUser(id);
    }

    @Override
    public void enableUser(Long id) {
        userServiceClient.enableUser(id);
    }
}
