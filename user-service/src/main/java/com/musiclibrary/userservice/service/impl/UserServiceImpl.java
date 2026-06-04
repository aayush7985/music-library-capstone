package com.musiclibrary.userservice.service.impl;

import com.musiclibrary.userservice.dto.*;
import com.musiclibrary.userservice.entity.User;
import com.musiclibrary.userservice.exception.EmailAlreadyExistsException;
import com.musiclibrary.userservice.exception.InvalidCredentialsException;
import com.musiclibrary.userservice.exception.UserNotFoundException;
import com.musiclibrary.userservice.repository.UserRepository;
import com.musiclibrary.userservice.service.UserService;
import com.musiclibrary.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDTO register(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(dto.getEmail());
        }
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(User.Role.ROLE_USER)
                .enabled(true)
                .build();
        User saved = userRepository.save(user);
        return toResponseDTO(saved);
    }

    @Override
    public JwtResponseDTO login(UserLoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        if (!user.isEnabled()) {
            throw new RuntimeException("Account is disabled. Contact admin.");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());
        return JwtResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return toResponseDTO(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdateUserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return toResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public void disableUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void enableUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
