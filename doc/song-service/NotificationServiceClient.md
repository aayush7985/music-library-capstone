# NotificationServiceClient.java

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/client/NotificationServiceClient.java`

## Kya Karta Hai?

Yeh Feign client Song Service ko Notification Service se baat karne ki suvidha deta hai. Jab naya song add hota hai, Song Service is client ke through Notification Service ko inform karta hai.

---

## Code

```java
@FeignClient(name = "notification-service", path = "/api/notifications")
public interface NotificationServiceClient {

    @PostMapping("/new-song")
    void notifyNewSong(@RequestBody NotificationRequestDTO dto);
}
```

---

## Explanation (Hinglish)

- `name = "notification-service"` — Eureka mein is naam se registered service ko dhundo.
- Sirf ek method hai: `notifyNewSong()` — notification create karne ke liye.
- Return type `void` — response ki zaroorat nahi, bas fire-and-forget.

---

## Flow Diagram

```
Admin adds song
      ↓
SongServiceImpl.addSong()
      ↓
notificationServiceClient.notifyNewSong(dto)  ← Feign call
      ↓
Notification Service ko POST /api/notifications/new-song
      ↓
Notification Service: Saare users ke liye notification create karo
      ↓
Har user ke dashboard par bell icon mein count badha
```

---

## Try-Catch Se Wrapped Hai

```java
try {
    notificationServiceClient.notifyNewSong(dto);
} catch (Exception e) {
    log.warn("Notification failed: {}", e.getMessage());
}
```

Agar Notification Service down hai — song phir bhi add hoga, sirf notification nahi jayega. Yeh **fault tolerance** hai.
