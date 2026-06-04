# PlaylistSongDTO.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/dto/PlaylistSongDTO.java`

## Kya Karta Hai?

PlaylistSong entity ko response mein return karne ke liye DTO.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistSongDTO {
    private Long id;         // PlaylistSong ki ID
    private Long songId;     // Song Service ka ID
    private String songTitle;
    private String singer;
    private Integer position;
    private LocalDateTime addedAt;
}
```

Playlist detail page par table mein yahi data show hota hai — position, title, singer, kab add kiya.
