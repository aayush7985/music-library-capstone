package com.musiclibrary.playlistservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AddSongDTO {
    @NotNull(message = "Song ID is required")
    private Long songId;
}
