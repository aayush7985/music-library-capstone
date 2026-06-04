# PlaylistServiceImpl.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/service/impl/PlaylistServiceImpl.java`

## Kya Karta Hai?

Playlist Service ka main business logic — playlist aur songs CRUD, search, aur Song Service Feign call.

---

## Code Explanation (Hinglish)

### addSongToPlaylist() — Most Complex Method
```java
public PlaylistResponseDTO addSongToPlaylist(Long playlistId, AddSongDTO dto) {
    // 1. Playlist exist karti hai?
    Playlist playlist = playlistRepository.findById(playlistId)
            .orElseThrow(() -> new PlaylistNotFoundException(playlistId));

    // 2. Song already playlist mein hai?
    if (playlistSongRepository.existsByPlaylistIdAndSongId(playlistId, dto.getSongId())) {
        throw new SongAlreadyInPlaylistException(dto.getSongId());
    }

    // 3. Song Service se song details fetch karo (Feign)
    SongResponseDTO song = songServiceClient.getSongById(dto.getSongId());

    // 4. PlaylistSong entry banao (title aur singer cache karo)
    int nextPosition = playlist.getSongs().size() + 1;
    PlaylistSong ps = PlaylistSong.builder()
            .playlist(playlist)
            .songId(song.getId())
            .songTitle(song.getTitle())   // Cache!
            .singer(song.getSinger())      // Cache!
            .position(nextPosition)
            .build();

    playlistSongRepository.save(ps);
    return toDTO(playlistRepository.findById(playlistId).get());
}
```

**Step by step:**
1. Playlist valid hai check karo
2. Duplicate song check karo — ek song ek playlist mein sirf ek baar
3. Song Service se real-time details fetch karo
4. Local table mein position aur cached data ke saath save karo

---

### searchSongsInPlaylist()
```java
public List<PlaylistSongDTO> searchSongsInPlaylist(Long playlistId, String title) {
    return playlistSongRepository.searchByTitle(playlistId, title)
            .stream().map(this::toSongDTO).collect(Collectors.toList());
}
```

Playlist ke andar songs ka naam search karo — locally stored `songTitle` field se search hota hai (Song Service call nahi hota).

---

### deletePlaylist() — Cascade
```java
public void deletePlaylist(Long id) {
    if (!playlistRepository.existsById(id)) throw new PlaylistNotFoundException(id);
    playlistRepository.deleteById(id);
}
```

Playlist delete hone par `CascadeType.ALL` ki wajah se **saare PlaylistSong entries bhi automatically delete** ho jaate hain — koi extra code nahi likhna padta.
