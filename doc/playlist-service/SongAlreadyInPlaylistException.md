# SongAlreadyInPlaylistException.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/exception/SongAlreadyInPlaylistException.java`

## Kya Karta Hai?

Jab user ek hi song dobara playlist mein add karne ki koshish kare.

```java
public class SongAlreadyInPlaylistException extends RuntimeException {
    public SongAlreadyInPlaylistException(Long songId) {
        super("Song with id " + songId + " is already in the playlist");
    }
}
```

**HTTP Response:** 409 Conflict — resource already exists (duplicate).

Used in: `PlaylistServiceImpl.addSongToPlaylist()` mein
```java
if (playlistSongRepository.existsByPlaylistIdAndSongId(playlistId, dto.getSongId())) {
    throw new SongAlreadyInPlaylistException(dto.getSongId());
}
```
