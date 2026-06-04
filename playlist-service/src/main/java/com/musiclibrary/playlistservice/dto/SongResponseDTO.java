package com.musiclibrary.playlistservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongResponseDTO {
    private Long id;
    private String title;
    private String singer;
    private String musicDirector;
    private String albumName;
    private String genre;
    private String duration;
    private String audioUrl;
}
