package com.musiclibrary.playlistservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistResponseDTO {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private List<PlaylistSongDTO> songs;
    private int songCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
