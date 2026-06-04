package com.musiclibrary.notificationservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationRequestDTO {
    private Long songId;
    private String songTitle;
    private String message;
}
