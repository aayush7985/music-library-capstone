# Notification.java (Entity)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/entity/Notification.java`

## Kya Karta Hai?

`notifications` table represent karta hai — har user ke liye ek notification entry.

---

## Code Explanation (Hinglish)

```java
@Entity
@Table(name = "notifications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;        // Kaun user ko yeh notification hai

    @Column(nullable = false)
    private Long songId;        // Kaunse song ke baare mein

    @Column(nullable = false)
    private String songTitle;   // Song ka naam

    @Column(nullable = false)
    private String message;     // "New song added: XYZ by ABC"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type = NotificationType.NEW_SONG;

    @Column(nullable = false)
    private boolean isRead = false;   // Padha ya nahi

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum NotificationType {
        NEW_SONG, GENERAL
    }
}
```

---

## Fields Ka Matlab

| Field | Default | Purpose |
|---|---|---|
| `userId` | — | User Service ka user ID — kis user ko dikhaana |
| `songId` | — | Song Service ka song ID |
| `songTitle` | — | Cached song naam (fast display) |
| `message` | — | "New song added: ..." |
| `type` | `NEW_SONG` | Notification ka type — future mein GENERAL bhi ho sakta |
| `isRead` | `false` | Unread = bell badge mein count aata hai |
| `createdAt` | auto | Kab aayi notification |

---

## Why Per-User Rows?

Ek song add hone par **N notifications** bante hain (N = total users).

**Kyun alag alag?**
- Har user apni notification independently mark-as-read kar sakta hai.
- Ek user ne padha toh doosre ki notification affect nahi hoti.
- `isRead` tracking per-user hoti hai.

Example: 100 users hain → 1 naya song → **100 notification rows** bante hain.
