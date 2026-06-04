package com.musiclibrary.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String email;
    private String name;
    private String role;
}
