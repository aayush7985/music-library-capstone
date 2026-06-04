# NotificationResponseDTO.java

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/dto/NotificationResponseDTO.java`

## Kya Karta Hai?

Frontend ko notification data return karne ke liye DTO.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationResponseDTO {
    private Long id;
    private Long userId;
    private Long songId;
    private String songTitle;
    private String message;
    private String type;
    private boolean isRead;
    private LocalDateTime createdAt;
}
```

Frontend mein yeh data bell dropdown mein show hota hai — har notification ek item hota hai jismein song naam, message, time ago, aur read status hoti hai.
