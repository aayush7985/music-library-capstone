package com.musiclibrary.songservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationRequestDTO {
    private Long songId;
    private String songTitle;
    private String message;
}
