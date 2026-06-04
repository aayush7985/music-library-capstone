# PlaylistNotFoundException.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/exception/PlaylistNotFoundException.java`

## Kya Karta Hai?

Jab diya gaya ID wali playlist database mein na mile.

```java
public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(Long id) {
        super("Playlist not found with id: " + id);
    }
}
```

**HTTP Response:** 404 Not Found via GlobalExceptionHandler.
