package com.musiclibrary.adminservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
