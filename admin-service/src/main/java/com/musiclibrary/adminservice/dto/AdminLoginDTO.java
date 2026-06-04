package com.musiclibrary.adminservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminLoginDTO {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
}
