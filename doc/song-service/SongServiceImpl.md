# SongServiceImpl.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/service/impl/SongServiceImpl.java`

## Kya Karta Hai?

Song Service ka main business logic. Sabse important feature: **naya song add hone par Notification Service ko trigger karna**.

---

## Code Explanation (Hinglish)

### addSong() — Most Important Method
```java
public SongResponseDTO addSong(SongCreateDTO dto) {
    // 1. Song entity banao aur save karo
    Song song = Song.builder()
            .title(dto.getTitle())
            .singer(dto.getSinger())
            .musicDirector(dto.getMusicDirector())
            .albumName(dto.getAlbumName())
            .releaseDate(dto.getReleaseDate())
            .genre(dto.getGenre())
            .duration(dto.getDuration())
            .audioUrl("dummy-audio.mp3")
            .visible(true)
            .build();
    Song saved = songRepository.save(song);

    // 2. Notification Service ko alert karo (try-catch mein — fail hone par song add nahi rukna chahiye)
    try {
        notificationServiceClient.notifyNewSong(NotificationRequestDTO.builder()
                .songId(saved.getId())
                .songTitle(saved.getTitle())
                .message("New song added: " + saved.getTitle() + " by " + saved.getSinger())
                .build());
    } catch (Exception e) {
        log.warn("Failed to send notification: {}", e.getMessage());
        // Warning log karo but exception propagate mat karo
    }

    return toDTO(saved);
}
```

**Why try-catch?**
Agar Notification Service down hai toh song add hona ruk nahi jaana chahiye. `try-catch` se ensure karte hain ki notification failure song creation ko affect nahi kare — yeh **graceful degradation** pattern hai.

---

### toggleVisibility() — Admin Feature
```java
public SongResponseDTO toggleVisibility(Long id) {
    Song song = songRepository.findById(id)
            .orElseThrow(() -> new SongNotFoundException(id));
    song.setVisible(!song.isVisible());  // true → false, false → true
    return toDTO(songRepository.save(song));
}
```

Simple toggle: agar `true` tha toh `false` karo, agar `false` tha toh `true` karo.

---

### searchSongs()
```java
public List<SongResponseDTO> searchSongs(String query) {
    return songRepository.searchSongs(query).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}
```

Repository ki search query call karo, result DTO list mein convert karke return karo.

---

### getAllVisibleSongs() vs getAllSongs()
```java
// Users ke liye — sirf visible songs
public List<SongResponseDTO> getAllVisibleSongs() {
    return songRepository.findByVisible(true)...
}

// Admin ke liye — hidden songs bhi
public List<SongResponseDTO> getAllSongs() {
    return songRepository.findAll()...
}
```
