# PlaylistController.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/controller/PlaylistController.java`

## Kya Karta Hai?

Playlist ke saare REST endpoints. `@RequestHeader("X-Auth-User-Id")` se user ki ID gateway se aati hai.

---

## Endpoints Table

| Method | URL | Kya Karta Hai |
|---|---|---|
| `POST` | `/api/playlists` | Naya playlist banao |
| `GET` | `/api/playlists` | User ke saare playlists |
| `GET` | `/api/playlists/{id}` | Ek playlist details |
| `PUT` | `/api/playlists/{id}` | Playlist naam/desc update |
| `DELETE` | `/api/playlists/{id}` | Playlist delete |
| `POST` | `/api/playlists/{id}/songs` | Song add karo |
| `DELETE` | `/api/playlists/{id}/songs/{songId}` | Song remove karo |
| `GET` | `/api/playlists/{id}/songs/search?title=` | Songs search |

---

## `@RequestHeader("X-Auth-User-Id")` — Important!

```java
@PostMapping
public ResponseEntity<PlaylistResponseDTO> createPlaylist(
        @RequestHeader("X-Auth-User-Id") Long userId,
        @Valid @RequestBody PlaylistCreateDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(playlistService.createPlaylist(userId, dto));
}
```

**Yeh header kahan se aata hai?**
- User JWT token mein login karte time `userId` store hota hai.
- API Gateway JWT validate karke `X-Auth-User-Id` header set karta hai.
- Playlist Service yeh header se user ID paata hai — kisi bhi query parameter ya body field ki zaroorat nahi.

**Kyun yeh approach?**
- User apna userId khud send nahi kar sakta (security risk — dusre user ka data access kar sakta).
- Gateway guaranteed verified JWT se userId nikalta hai — trusted source.

---

## Song Search in Playlist
```java
@GetMapping("/{id}/songs/search")
public ResponseEntity<List<PlaylistSongDTO>> searchSongs(
        @PathVariable Long id, @RequestParam String title) {
    return ResponseEntity.ok(playlistService.searchSongsInPlaylist(id, title));
}
```

URL: `/api/playlists/3/songs/search?title=love`
