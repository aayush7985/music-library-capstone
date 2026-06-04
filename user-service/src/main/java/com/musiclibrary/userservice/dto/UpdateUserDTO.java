package com.musiclibrary.userservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserDTO {
    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    private String password;
}
