package com.musiclibrary.songservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongResponseDTO {
    private Long id;
    private String title;
    private String singer;
    private String musicDirector;
    private String albumName;
    private LocalDate releaseDate;
    private String genre;
    private String duration;
    private String audioUrl;
    private boolean visible;
    private LocalDateTime createdAt;
}
