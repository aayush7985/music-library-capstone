package com.musiclibrary.playlistservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistCreateDTO {
    @NotBlank(message = "Playlist name is required")
    private String name;
    private String description;
}
