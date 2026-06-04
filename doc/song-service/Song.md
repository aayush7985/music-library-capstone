# Song.java (Entity)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/entity/Song.java`

## Kya Karta Hai?

`songs` database table represent karta hai — ek song ki saari information ek row mein.

---

## Code Explanation (Hinglish)

```java
@Entity
@Table(name = "songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Song {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Singer is required")
    @Column(nullable = false)
    private String singer;

    @NotBlank(message = "Music director is required")
    @Column(nullable = false)
    private String musicDirector;

    @Column private String albumName;
    @Column private LocalDate releaseDate;
    @Column private String genre;
    @Column private String duration;

    @Column
    private String audioUrl = "dummy-audio.mp3";

    @Column(nullable = false)
    private boolean visible = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
```

---

## Fields Ka Matlab

| Field | Required | Default | Purpose |
|---|---|---|---|
| `title` | ✓ | — | Song ka naam |
| `singer` | ✓ | — | Singer ka naam |
| `musicDirector` | ✓ | — | Music director |
| `albumName` | ✗ | null | Album (optional) |
| `releaseDate` | ✗ | null | Release date |
| `genre` | ✗ | null | Pop, Rock, Bollywood etc. |
| `duration` | ✗ | null | "3:45" format |
| `audioUrl` | — | `"dummy-audio.mp3"` | Audio file ka path (dummy) |
| `visible` | — | `true` | Admin visibility control |
| `createdAt` | — | auto | Jab add kiya |

---

## `visible` Field — Admin Feature

Yeh field admin ko song **hide/show** karne ki power deta hai:
- `visible = true` — Normal users ko dikhega
- `visible = false` — Users ko nahi dikhega (sirf admin dekh sakta hai `/api/songs/all` se)

---

## `audioUrl = "dummy-audio.mp3"`

Actual audio files nahi hain project mein — yeh ek placeholder hai. Real application mein yahan cloud storage URL hoga (S3, Cloudinary etc.).
