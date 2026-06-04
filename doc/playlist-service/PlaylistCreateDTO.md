# PlaylistCreateDTO.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/dto/PlaylistCreateDTO.java`

## Kya Karta Hai?

Playlist create ya update karne ke request body ke liye DTO.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistCreateDTO {
    @NotBlank(message = "Playlist name is required")
    private String name;
    private String description;  // Optional
}
```

Simple 2 fields — naam zaroori, description optional. `userId` yahan nahi — wo Gateway ke `X-Auth-User-Id` header se aata hai (safe approach).
