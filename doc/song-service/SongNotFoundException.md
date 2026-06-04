# SongNotFoundException.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/exception/SongNotFoundException.java`

## Kya Karta Hai?

Jab diya gaya ID wala song database mein nahi milta tab throw hoti hai.

```java
public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Long id) {
        super("Song not found with id: " + id);
    }
}
```

Use: `songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id))`
GlobalExceptionHandler se **404 Not Found** response generate hota hai.
