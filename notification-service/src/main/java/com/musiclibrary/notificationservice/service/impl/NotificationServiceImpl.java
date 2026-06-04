package com.musiclibrary.notificationservice.service.impl;

import com.musiclibrary.notificationservice.client.UserServiceClient;
import com.musiclibrary.notificationservice.dto.NotificationRequestDTO;
import com.musiclibrary.notificationservice.dto.NotificationResponseDTO;
import com.musiclibrary.notificationservice.dto.UserResponseDTO;
import com.musiclibrary.notificationservice.entity.Notification;
import com.musiclibrary.notificationservice.repository.NotificationRepository;
import com.musiclibrary.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserServiceClient userServiceClient;

    @Override
    public void createNotificationsForAllUsers(NotificationRequestDTO dto) {
        try {
            List<UserResponseDTO> users = userServiceClient.getAllUsers();
            List<Notification> notifications = users.stream()
                    .filter(UserResponseDTO::isEnabled)
                    .map(user -> Notification.builder()
                            .userId(user.getId())
                            .songId(dto.getSongId())
                            .songTitle(dto.getSongTitle())
                            .message(dto.getMessage())
                            .type(Notification.NotificationType.NEW_SONG)
                            .isRead(false)
                            .build())
                    .collect(Collectors.toList());
            notificationRepository.saveAll(notifications);
            log.info("Created {} notifications for new song: {}", notifications.size(), dto.getSongTitle());
        } catch (Exception e) {
            log.error("Failed to create notifications: {}", e.getMessage());
        }
    }

    @Override
    public List<NotificationResponseDTO> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    @Override
    public NotificationResponseDTO markAsRead(Long notificationId) {
        Notification n = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found: " + notificationId));
        n.setRead(true);
        return toDTO(notificationRepository.save(n));
    }

    @Override
    public void markAllAsRead(Long userId) {
        List<Notification> unread = notificationRepository
                .findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
        unread.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(unread);
    }

    private NotificationResponseDTO toDTO(Notification n) {
        return NotificationResponseDTO.builder()
                .id(n.getId())
                .userId(n.getUserId())
                .songId(n.getSongId())
                .songTitle(n.getSongTitle())
                .message(n.getMessage())
                .type(n.getType().name())
                .isRead(n.isRead())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
