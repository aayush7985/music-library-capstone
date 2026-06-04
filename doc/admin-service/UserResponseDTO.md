# UserResponseDTO.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/dto/UserResponseDTO.java`

## Kya Karta Hai?

Jab Admin Service User Service se Feign call karke users data laati hai, woh data is DTO mein map hota hai.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean enabled;
    private LocalDateTime createdAt;
}
```

User Service ke UserResponseDTO se bilkul same structure — **purposely same rakhna zaroori hai** taaki Feign client response ko sahi se deserialize kar sake (JSON → DTO). Password yahan bhi nahi hai.
