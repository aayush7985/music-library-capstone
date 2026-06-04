# SongServiceApplication.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/SongServiceApplication.java`

## Kya Karta Hai?

Song Service ka entry point. Yeh service songs ka **CRUD, search, visibility management** karta hai aur jab naya song add hota hai **Notification Service ko alert** karta hai.

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SongServiceApplication { ... }
```

`@EnableFeignClients` — Notification Service ko Feign se call karne ke liye zaroori.

## Port: **8083** | Database: `jdbc:h2:mem:song_db`

## Is Service Ki Key Responsibilities:
- Admin songs add/edit/delete kar sakta hai
- Visibility toggle (visible/hidden)
- Users songs search kar sakte hain
- Naya song add hone par Notification Service ko trigger karna
- 5 sample songs startup par load hote hain (DataInitializer se)
