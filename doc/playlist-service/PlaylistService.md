# PlaylistService.java (Interface)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/service/PlaylistService.java`

## Kya Karta Hai?

Playlist Service ka contract define karta hai.

```java
public interface PlaylistService {
    PlaylistResponseDTO createPlaylist(Long userId, PlaylistCreateDTO dto);
    List<PlaylistResponseDTO> getUserPlaylists(Long userId);
    PlaylistResponseDTO getPlaylistById(Long id);
    PlaylistResponseDTO updatePlaylist(Long id, PlaylistCreateDTO dto);
    void deletePlaylist(Long id);
    PlaylistResponseDTO addSongToPlaylist(Long playlistId, AddSongDTO dto);
    void removeSongFromPlaylist(Long playlistId, Long songId);
    List<PlaylistSongDTO> searchSongsInPlaylist(Long playlistId, String title);
}
```

8 operations: Playlist CRUD (4) + Song operations (3) + Search (1). `userId` parameter createPlaylist aur getUserPlaylists mein zaroori hai kyunki Gateway se `X-Auth-User-Id` aata hai.
