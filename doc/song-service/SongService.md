# SongService.java (Interface)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/service/SongService.java`

## Kya Karta Hai?

Song Service ka contract — kya kya operations possible hain.

```java
public interface SongService {
    SongResponseDTO addSong(SongCreateDTO dto);
    SongResponseDTO updateSong(Long id, SongCreateDTO dto);
    void deleteSong(Long id);
    SongResponseDTO getSongById(Long id);
    List<SongResponseDTO> getAllVisibleSongs();   // Users ke liye
    List<SongResponseDTO> getAllSongs();           // Admin ke liye
    List<SongResponseDTO> searchSongs(String query);
    SongResponseDTO toggleVisibility(Long id);
}
```

`getAllVisibleSongs()` aur `getAllSongs()` ka fark: visibility filter. Users hidden songs nahi dekh sakte, admin sab dekh sakta hai.
