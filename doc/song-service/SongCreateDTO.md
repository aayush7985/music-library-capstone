# SongCreateDTO.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/dto/SongCreateDTO.java`

## Kya Karta Hai?

Admin jab naya song add karta hai ya update karta hai, request ka data is DTO mein aata hai.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongCreateDTO {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Singer is required")
    private String singer;
    @NotBlank(message = "Music director is required")
    private String musicDirector;
    private String albumName;       // Optional
    private LocalDate releaseDate;  // Optional
    private String genre;           // Optional
    private String duration;        // Optional
}
```

**Note:** `audioUrl` aur `visible` yahan nahi hain — `audioUrl` hamesha `"dummy-audio.mp3"` set hota hai (service layer mein), aur `visible` default `true` hota hai. Yeh admin se lene ki zaroorat nahi.
