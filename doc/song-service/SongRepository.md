# SongRepository.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/repository/SongRepository.java`

## Kya Karta Hai?

Song database operations ke liye repository. Custom search queries bhi define ki hain.

---

## Code Explanation (Hinglish)

```java
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByVisible(boolean visible);

    @Query("SELECT s FROM Song s WHERE s.visible = true AND " +
           "(LOWER(s.singer) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.albumName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.musicDirector) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.title) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Song> searchSongs(@Param("query") String query);
}
```

---

### `findByVisible(true)` — Simple Derived Query
- `SELECT * FROM songs WHERE visible = true`
- Users ke liye sirf visible songs dikhane ke liye.

---

### `searchSongs()` — Custom JPQL Query
- `@Query` annotation se custom query likh sakte hain.
- `LOWER()` — Case-insensitive search (hindi/english dono).
- `LIKE '%query%'` — Partial match — "ar" likhne par "Arijit", "Arjun" dono milenge.
- 4 fields par search: **title, singer, albumName, musicDirector** — sab ek saath.

**Example:**
```
searchSongs("Arijit")
→ "Tum Hi Ho" (singer: Arijit Singh) ✓
→ "Kesariya" (singer: Arijit Singh) ✓
```

---

### JPQL vs SQL

JPQL (Java Persistence Query Language) — Entity names aur field names use karta hai, table names nahi:
```
JPQL: SELECT s FROM Song s WHERE s.singer LIKE ...
SQL:  SELECT * FROM songs WHERE singer LIKE ...
```
Yeh database-independent rehta hai.
