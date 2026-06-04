package com.musiclibrary.notificationservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private boolean enabled;
}
