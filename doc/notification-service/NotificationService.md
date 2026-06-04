# NotificationService.java (Interface)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/service/NotificationService.java`

## Kya Karta Hai?

Notification Service ka contract.

```java
public interface NotificationService {
    void createNotificationsForAllUsers(NotificationRequestDTO dto);
    List<NotificationResponseDTO> getUserNotifications(Long userId);
    long getUnreadCount(Long userId);
    NotificationResponseDTO markAsRead(Long notificationId);
    void markAllAsRead(Long userId);
}
```

| Method | Kab Use |
|---|---|
| `createNotificationsForAllUsers` | Song add hone par (Song Service trigger karta hai) |
| `getUserNotifications` | Bell dropdown open karne par |
| `getUnreadCount` | Dashboard load par badge number ke liye |
| `markAsRead` | Single notification click par |
| `markAllAsRead` | "Mark all read" button par |
