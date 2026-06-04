# PlaylistSongRepository.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/repository/PlaylistSongRepository.java`

## Kya Karta Hai?

`playlist_songs` table ke operations ke liye repository — songs add/remove/search karna.

---

## Code

```java
@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {

    List<PlaylistSong> findByPlaylistId(Long playlistId);

    Optional<PlaylistSong> findByPlaylistIdAndSongId(Long playlistId, Long songId);

    boolean existsByPlaylistIdAndSongId(Long playlistId, Long songId);

    @Query("SELECT ps FROM PlaylistSong ps WHERE ps.playlist.id = :playlistId " +
           "AND LOWER(ps.songTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<PlaylistSong> searchByTitle(@Param("playlistId") Long playlistId,
                                     @Param("title") String title);
}
```

---

## Methods Ka Matlab

| Method | Kab Use Hota Hai |
|---|---|
| `findByPlaylistId` | Playlist ke saare songs lo |
| `findByPlaylistIdAndSongId` | Remove karne se pehle PlaylistSong entry dhundho |
| `existsByPlaylistIdAndSongId` | Duplicate song check — `SongAlreadyInPlaylistException` |
| `searchByTitle` | Playlist ke andar song naam se search |

---

## `searchByTitle` — JPQL Query

```sql
SELECT ps FROM PlaylistSong ps
WHERE ps.playlist.id = :playlistId
AND LOWER(ps.songTitle) LIKE LOWER(CONCAT('%', :title, '%'))
```

- Sirf is playlist ke songs mein search karo.
- `LOWER()` — Case-insensitive: "love", "Love", "LOVE" sab match karega.
- Locally stored `songTitle` field use karta hai — Song Service call nahi hota.
