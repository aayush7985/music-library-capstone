# NotificationRepository.java

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/repository/NotificationRepository.java`

## Kya Karta Hai?

Notification database operations ke liye JPA repository.

```java
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Notification> findByUserIdAndIsReadFalseOrderByCreatedAtDesc(Long userId);

    long countByUserIdAndIsReadFalse(Long userId);
}
```

---

## Methods Ka Matlab

| Method | Auto-generated SQL | Use |
|---|---|---|
| `findByUserIdOrderByCreatedAtDesc` | `WHERE user_id = ? ORDER BY created_at DESC` | User ki saari notifications (newest first) |
| `findByUserIdAndIsReadFalseOrderByCreatedAtDesc` | `WHERE user_id = ? AND is_read = false ORDER BY created_at DESC` | Sirf unread notifications |
| `countByUserIdAndIsReadFalse` | `COUNT WHERE user_id = ? AND is_read = false` | Bell badge number |

**Naming conventions se SQL generation:**
- `OrderByCreatedAtDesc` → `ORDER BY created_at DESC`
- `AndIsReadFalse` → `AND is_read = false`
