# SongResponseDTO.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/dto/SongResponseDTO.java`

## Kya Karta Hai?

Song data return karne ke liye DTO — entity ki saari information jo bahar share kar sakte hain.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongResponseDTO {
    private Long id;
    private String title;
    private String singer;
    private String musicDirector;
    private String albumName;
    private LocalDate releaseDate;
    private String genre;
    private String duration;
    private String audioUrl;
    private boolean visible;
    private LocalDateTime createdAt;
}
```

Song Entity se saare fields include hain kyunki songs mein koi sensitive data nahi hai. `visible` field bhi include hai taaki admin UI hidden songs ko mark kar sake.
