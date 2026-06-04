# AddSongDTO.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/dto/AddSongDTO.java`

## Kya Karta Hai?

Playlist mein song add karne ki request ke liye minimal DTO.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AddSongDTO {
    @NotNull(message = "Song ID is required")
    private Long songId;
}
```

Sirf `songId` chahiye — bass itna batao ki kaunsa song add karna hai. Title, singer sab Song Service se Feign ke through fetch ho jaata hai.

```
POST /api/playlists/3/songs
Body: { "songId": 5 }
```
