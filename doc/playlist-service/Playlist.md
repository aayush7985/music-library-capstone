# Playlist.java (Entity)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/entity/Playlist.java`

## Kya Karta Hai?

`playlists` table represent karta hai — ek playlist ki saari information.

---

## Code Explanation (Hinglish)

```java
@Entity
@Table(name = "playlists")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Playlist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;           // Kaun sa user ka hai (User Service ID)

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PlaylistSong> songs = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
```

---

## Important Fields

### `userId`
```java
private Long userId;
```
- Yeh User Service ka ID hai — lekin foreign key nahi hai kyunki **alag database** hai!
- Microservices mein cross-database foreign keys nahi hoti — sirf ID reference hota hai.

### `@OneToMany` Relationship
```java
@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
private List<PlaylistSong> songs = new ArrayList<>();
```

- **One-to-Many**: Ek playlist mein bahut saare songs ho sakte hain.
- `cascade = ALL` — Playlist delete ho toh uske saare songs bhi delete ho jaayein.
- `orphanRemoval = true` — PlaylistSong ko playlist se remove karo toh database se bhi delete ho.
- `mappedBy = "playlist"` — PlaylistSong entity mein `playlist` field is relationship ko own karta hai.

### `@PreUpdate`
```java
protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
}
```
Playlist update hone par `updatedAt` automatically current time set ho jaata hai.
