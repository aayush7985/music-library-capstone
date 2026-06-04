# PlaylistSong.java (Entity)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/entity/PlaylistSong.java`

## Kya Karta Hai?

`playlist_songs` table represent karta hai — playlist aur song ke beech ka **junction table** (Many-to-Many relationship ke liye).

---

## Code Explanation (Hinglish)

```java
@Entity
@Table(name = "playlist_songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistSong {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @Column(nullable = false)
    private Long songId;        // Song Service ka ID

    @Column
    private String songTitle;   // Cached data (Song Service se laya)

    @Column
    private String singer;      // Cached data

    @Column
    private Integer position;   // Playlist mein position (1, 2, 3...)

    @Column(nullable = false, updatable = false)
    private LocalDateTime addedAt;
}
```

---

## Interesting Design: Denormalization

`songTitle` aur `singer` yahan **cache** ki tarah store kiye gaye hain:

**Kyun?**
Har baar jab playlist load ho, Song Service ko call karna padta. 10 songs hain toh 10 Feign calls — yeh slow hoga.

**Solution:** Jab song add karo, Song Service se title aur singer fetch karo aur local table mein store kar lo. Aage playlist show karne par Song Service call karne ki zaroorat nahi.

---

### `@ManyToOne` Relationship
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "playlist_id", nullable = false)
private Playlist playlist;
```

- **Many-to-One**: Bahut saare PlaylistSong ek Playlist se belong karte hain.
- `FetchType.LAZY` — Playlist data tabhi load hoga jab actually chahiye (performance optimization).
- `@JoinColumn` — Database mein `playlist_id` foreign key column banega.

---

### `songId` — Cross-Service Reference
```java
private Long songId;  // Song Service mein yeh ID hai
```

Actual song data Song Service mein hai — yahan sirf ID store hai. Yeh microservice architecture ka principle hai — alag services ke data ke liye sirf IDs rakhte hain.
