# SongResponseDTO.java (Playlist Service)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/dto/SongResponseDTO.java`

## Kya Karta Hai?

Jab Playlist Service Song Service ko Feign se call karta hai, response is DTO mein map hota hai.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongResponseDTO {
    private Long id;
    private String title;
    private String singer;
    private String musicDirector;
    private String albumName;
    private String genre;
    private String duration;
    private String audioUrl;
}
```

Song Service ka SongResponseDTO se **similar structure** rakhna zaroori hai — Feign JSON response ko is class mein deserialize karta hai. Sirf woh fields jo Playlist Service ko chahiye — `visible`, `createdAt` chhod diye.
