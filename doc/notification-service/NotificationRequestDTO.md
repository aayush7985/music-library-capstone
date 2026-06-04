# NotificationRequestDTO.java (Notification Service)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/dto/NotificationRequestDTO.java`

## Kya Karta Hai?

Song Service se Notification Service ko bheja jaane wala data.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationRequestDTO {
    private Long songId;
    private String songTitle;
    private String message;
}
```

Song Service Feign se yeh bhejta hai:
```json
{
  "songId": 6,
  "songTitle": "Levitating",
  "message": "New song added: Levitating by Dua Lipa"
}
```
