package com.musiclibrary.adminservice.service;

import com.musiclibrary.adminservice.dto.*;

import java.util.List;

public interface AdminService {
    JwtResponseDTO login(AdminLoginDTO dto);
    AdminResponseDTO getAdminById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UpdateUserDTO dto);
    void deleteUser(Long id);
    void disableUser(Long id);
    void enableUser(Long id);
}
