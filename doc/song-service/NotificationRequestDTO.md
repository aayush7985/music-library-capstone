# NotificationRequestDTO.java (Song Service)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/dto/NotificationRequestDTO.java`

## Kya Karta Hai?

Jab Song Service Notification Service ko Feign se call karta hai, yeh DTO data carry karta hai.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationRequestDTO {
    private Long songId;
    private String songTitle;
    private String message;
}
```

**Example data:**
```json
{
  "songId": 6,
  "songTitle": "Blinding Lights",
  "message": "New song added: Blinding Lights by The Weeknd"
}
```

Notification Service yeh data receive karke saare registered users ke liye notification entries create karta hai.
