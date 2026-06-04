# NotificationServiceImpl.java

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/service/impl/NotificationServiceImpl.java`

## Kya Karta Hai?

Notification Service ka main business logic — notifications create karna, padhna, mark as read karna.

---

## Code Explanation (Hinglish)

### createNotificationsForAllUsers() — Main Method
```java
public void createNotificationsForAllUsers(NotificationRequestDTO dto) {
    try {
        // 1. User Service se saare users fetch karo
        List<UserResponseDTO> users = userServiceClient.getAllUsers();

        // 2. Har enabled user ke liye notification banao
        List<Notification> notifications = users.stream()
                .filter(UserResponseDTO::isEnabled)  // Disabled users ko skip karo
                .map(user -> Notification.builder()
                        .userId(user.getId())
                        .songId(dto.getSongId())
                        .songTitle(dto.getSongTitle())
                        .message(dto.getMessage())
                        .type(Notification.NotificationType.NEW_SONG)
                        .isRead(false)
                        .build())
                .collect(Collectors.toList());

        // 3. Batch save karo — ek hi DB call mein sab save
        notificationRepository.saveAll(notifications);

        log.info("Created {} notifications for new song: {}",
                 notifications.size(), dto.getSongTitle());
    } catch (Exception e) {
        log.error("Failed to create notifications: {}", e.getMessage());
    }
}
```

**Key points:**
- Disabled users ko filter kiya — unhe notifications nahi chahiye (banned hain).
- `saveAll()` — Efficient batch insert — ek ek karke save karne se zyada fast.
- `try-catch` — User Service down ho toh bhi gracefully fail ho.

---

### markAsRead() Method
```java
public NotificationResponseDTO markAsRead(Long notificationId) {
    Notification n = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
    n.setRead(true);
    return toDTO(notificationRepository.save(n));
}
```

Ek notification read mark karo.

---

### markAllAsRead() Method
```java
public void markAllAsRead(Long userId) {
    List<Notification> unread = notificationRepository
            .findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
    unread.forEach(n -> n.setRead(true));
    notificationRepository.saveAll(unread);
}
```

User ke saare unread notifications ek baar mein mark as read.

---

### getUnreadCount()
```java
public long getUnreadCount(Long userId) {
    return notificationRepository.countByUserIdAndIsReadFalse(userId);
}
```

Bell icon badge ke liye count return karo. Frontend yeh call polling ya page load par karta hai.
