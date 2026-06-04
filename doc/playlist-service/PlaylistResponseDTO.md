# PlaylistResponseDTO.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/dto/PlaylistResponseDTO.java`

## Kya Karta Hai?

Playlist data response mein return karne ke liye DTO — saari songs list ke saath.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistResponseDTO {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private List<PlaylistSongDTO> songs;  // Songs ki list!
    private int songCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

`songs` field mein playlist ke saare songs ki list hoti hai — nested DTO. `songCount` frontend mein "5 songs" dikhane ke liye convenient shortcut hai.
