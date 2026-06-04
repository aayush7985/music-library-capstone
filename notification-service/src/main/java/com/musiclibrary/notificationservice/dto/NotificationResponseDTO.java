package com.musiclibrary.notificationservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationResponseDTO {
    private Long id;
    private Long userId;
    private Long songId;
    private String songTitle;
    private String message;
    private String type;
    private boolean isRead;
    private LocalDateTime createdAt;
}
