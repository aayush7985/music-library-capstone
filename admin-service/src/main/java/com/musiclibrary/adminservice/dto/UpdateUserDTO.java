package com.musiclibrary.adminservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateUserDTO {
    private String name;
    private String phoneNumber;
    private String password;
}
