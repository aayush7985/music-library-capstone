# SongController.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/controller/SongController.java`

## Kya Karta Hai?

Song Service ke saare REST endpoints define karta hai — admin aur user dono ke liye.

---

## Endpoints Table

| HTTP Method | URL | Access | Kya Karta Hai |
|---|---|---|---|
| `POST` | `/api/songs` | Admin | Naya song add karo |
| `GET` | `/api/songs` | Public | Saare visible songs lo |
| `GET` | `/api/songs/all` | Admin | Saare songs (hidden bhi) |
| `GET` | `/api/songs/{id}` | Public | Ek song ki details |
| `PUT` | `/api/songs/{id}` | Admin | Song update karo |
| `DELETE` | `/api/songs/{id}` | Admin | Song delete karo |
| `PATCH` | `/api/songs/{id}/visibility` | Admin | Visibility toggle karo |
| `GET` | `/api/songs/search?query=` | Public | Search karo |

---

## Code Examples (Hinglish)

### Search Endpoint
```java
@GetMapping("/search")
public ResponseEntity<List<SongResponseDTO>> searchSongs(@RequestParam String query) {
    return ResponseEntity.ok(songService.searchSongs(query));
}
```
- `@RequestParam` — URL query parameter: `/api/songs/search?query=Arijit`
- Title, singer, album, music director sab mein search hoga.

### Visibility Toggle
```java
@PatchMapping("/{id}/visibility")
public ResponseEntity<SongResponseDTO> toggleVisibility(@PathVariable Long id) {
    return ResponseEntity.ok(songService.toggleVisibility(id));
}
```
- `@PatchMapping` — PATCH method partial update ke liye sahi hai.
- Yeh admin ka **song hide/show** feature implement karta hai.

### Song Add (Admin)
```java
@PostMapping
public ResponseEntity<SongResponseDTO> addSong(@Valid @RequestBody SongCreateDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(dto));
}
```
Song add hote hi automatically Notification Service trigger hota hai (service layer mein).
