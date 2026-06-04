# NotificationController.java

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/controller/NotificationController.java`

## Kya Karta Hai?

Notification Service ke REST endpoints — trigger, read, mark as read.

---

## Endpoints Table

| Method | URL | Kya Karta Hai | Caller |
|---|---|---|---|
| `POST` | `/api/notifications/new-song` | Notifications create karo (bulk) | Song Service (Feign) |
| `GET` | `/api/notifications/user/{userId}` | User ki saari notifications | Frontend |
| `GET` | `/api/notifications/user/{userId}/unread-count` | Unread count (bell badge) | Frontend |
| `PATCH` | `/api/notifications/{id}/read` | Ek notification read mark karo | Frontend |
| `PATCH` | `/api/notifications/user/{userId}/read-all` | Saari notifications read mark karo | Frontend |

---

## Code Example

```java
@PostMapping("/new-song")
@Operation(summary = "Trigger notifications for new song (internal - called by Song Service)")
public ResponseEntity<Map<String, String>> notifyNewSong(@RequestBody NotificationRequestDTO dto) {
    notificationService.createNotificationsForAllUsers(dto);
    return ResponseEntity.ok(Map.of("message", "Notifications created successfully"));
}
```

Yeh endpoint **internal** hai — sirf Song Service Feign se call karta hai. Normal users ya admin directly call nahi karte.

---

## Bell Icon Flow

```javascript
// Frontend dashboard load hone par:
const res = await apiGet(`/api/notifications/user/${userId}/unread-count`);
badge.textContent = res.data.unreadCount;  // "3" dikhe badge mein

// Bell click par:
const notifs = await apiGet(`/api/notifications/user/${userId}`);
// Dropdown mein notifications show karo

// Notification click par:
await apiPatch(`/api/notifications/${notifId}/read`);
// Badge count -1 karo
```
