package com.musiclibrary.notificationservice.controller;

import com.musiclibrary.notificationservice.dto.NotificationRequestDTO;
import com.musiclibrary.notificationservice.dto.NotificationResponseDTO;
import com.musiclibrary.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification API", description = "In-app notifications for users")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/new-song")
    @Operation(summary = "Trigger notifications for new song (internal - called by Song Service)")
    public ResponseEntity<Map<String, String>> notifyNewSong(@RequestBody NotificationRequestDTO dto) {
        notificationService.createNotificationsForAllUsers(dto);
        return ResponseEntity.ok(Map.of("message", "Notifications created successfully"));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all notifications for user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @GetMapping("/user/{userId}/unread-count")
    @Operation(summary = "Get unread notification count for user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, Long>> getUnreadCount(@PathVariable Long userId) {
        return ResponseEntity.ok(Map.of("unreadCount", notificationService.getUnreadCount(userId)));
    }

    @PatchMapping("/{id}/read")
    @Operation(summary = "Mark notification as read", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<NotificationResponseDTO> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @PatchMapping("/user/{userId}/read-all")
    @Operation(summary = "Mark all notifications as read", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> markAllAsRead(@PathVariable Long userId) {
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok(Map.of("message", "All notifications marked as read"));
    }
}
