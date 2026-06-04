# GlobalExceptionHandler.java (Playlist Service)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/exception/GlobalExceptionHandler.java`

## Kya Karta Hai?

Playlist Service ki exceptions handle karta hai.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PlaylistNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(SongAlreadyInPlaylistException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(SongAlreadyInPlaylistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)  // 409
                .body(new ErrorResponse(409, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)...
    }
}
```

| Exception | HTTP Status |
|---|---|
| `PlaylistNotFoundException` | 404 Not Found |
| `SongAlreadyInPlaylistException` | 409 Conflict |
| Generic Exception | 500 Internal Server Error |
