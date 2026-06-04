# UserResponseDTO.java (Notification Service)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/dto/UserResponseDTO.java`

## Kya Karta Hai?

User Service se Feign ke through aaye user data ko hold karne ke liye DTO.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private boolean enabled;
}
```

Notification Service ko sirf `id` (notifications create karne ke liye) aur `enabled` (disabled users skip karne ke liye) chahiye. `name` aur `email` optional hain — future use ke liye rakhe (jaise email notifications add karna ho baad mein).
