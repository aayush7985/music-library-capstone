package com.musiclibrary.songservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongCreateDTO {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Singer is required")
    private String singer;
    @NotBlank(message = "Music director is required")
    private String musicDirector;
    private String albumName;
    private LocalDate releaseDate;
    private String genre;
    private String duration;
}
