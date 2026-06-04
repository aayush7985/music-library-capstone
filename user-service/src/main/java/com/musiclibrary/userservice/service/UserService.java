package com.musiclibrary.userservice.service;

import com.musiclibrary.userservice.dto.*;

import java.util.List;

public interface UserService {
    UserResponseDTO register(UserRegistrationDTO dto);
    JwtResponseDTO login(UserLoginDTO dto);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UpdateUserDTO dto);
    void deleteUser(Long id);
    void disableUser(Long id);
    void enableUser(Long id);
}
