package com.musiclibrary.notificationservice.service;

import com.musiclibrary.notificationservice.dto.NotificationRequestDTO;
import com.musiclibrary.notificationservice.dto.NotificationResponseDTO;

import java.util.List;
import java.util.Map;

public interface NotificationService {
    void createNotificationsForAllUsers(NotificationRequestDTO dto);
    List<NotificationResponseDTO> getUserNotifications(Long userId);
    long getUnreadCount(Long userId);
    NotificationResponseDTO markAsRead(Long notificationId);
    void markAllAsRead(Long userId);
}
