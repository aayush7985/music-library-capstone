package com.musiclibrary.adminservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long adminId;
    private String email;
    private String name;
    private String role;
}
