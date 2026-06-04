# PlaylistServiceApplication.java

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/PlaylistServiceApplication.java`

## Kya Karta Hai?

Playlist Service ka entry point. Users apni playlists create, manage aur songs add/remove kar sakte hain.

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PlaylistServiceApplication { ... }
```

`@EnableFeignClients` — Song Service ko Feign se call karna hota hai (jab song playlist mein add karte hain, song details verify karne ke liye).

## Port: **8084** | Database: `jdbc:h2:mem:playlist_db`

## Is Service Mein 2 Tables Hain:
1. `playlists` — Playlist ka naam, description, userId
2. `playlist_songs` — Playlist ke andar songs (songId, title, singer store hota hai)

## Key Feature: Cross-Service Data
User ka data User Service mein hai, songs Song Service mein — Playlist Service sirf IDs store karta hai aur details Feign se fetch karta hai.
