package com.musiclibrary.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean enabled;
    private LocalDateTime createdAt;
}
