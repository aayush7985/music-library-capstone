# SongServiceClient.java (Playlist Service)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/client/SongServiceClient.java`

## Kya Karta Hai?

Playlist Service ko Song Service se song details fetch karne deta hai jab koi song playlist mein add kiya jaata hai.

```java
@FeignClient(name = "song-service", path = "/api/songs")
public interface SongServiceClient {

    @GetMapping("/{id}")
    SongResponseDTO getSongById(@PathVariable Long id);
}
```

---

## Use Case

Jab user playlist mein `songId = 3` add karta hai:
1. `songServiceClient.getSongById(3)` call hota hai
2. Song Service se title, singer fetch hota hai
3. Yeh data `PlaylistSong` entity mein cache ho jaata hai
4. Aage playlist show karne par dobara Song Service call nahi karna padta

---

## Agar Song Service Down Ho?

```java
SongResponseDTO song = songServiceClient.getSongById(dto.getSongId());
// Feign FeignException throw karega
```

Is scenario mein GlobalExceptionHandler 500 error return karega. Resilience pattern (circuit breaker) add kiya ja sakta hai future improvement ke taur par.
