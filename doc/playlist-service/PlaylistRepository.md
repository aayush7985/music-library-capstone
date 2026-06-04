# PlaylistRepository.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/repository/PlaylistRepository.java`

## Kya Karta Hai?

Playlist database operations ke liye JPA repository.

```java
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUserId(Long userId);
}
```

Sirf ek custom method hai:
- `findByUserId(Long userId)` → `SELECT * FROM playlists WHERE user_id = ?`
- Jab user apni playlists dekhna chahta hai tab use hoti hai.

JpaRepository se free mein: `save()`, `findById()`, `findAll()`, `deleteById()`, `existsById()`.
