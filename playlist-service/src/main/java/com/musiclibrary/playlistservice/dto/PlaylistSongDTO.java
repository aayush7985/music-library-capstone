package com.musiclibrary.playlistservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistSongDTO {
    private Long id;
    private Long songId;
    private String songTitle;
    private String singer;
    private Integer position;
    private LocalDateTime addedAt;
}
