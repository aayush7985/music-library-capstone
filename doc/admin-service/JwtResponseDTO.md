# JwtResponseDTO.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/dto/JwtResponseDTO.java`

## Kya Karta Hai?

Admin login ke baad return hone wala DTO — JWT token aur admin info.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long adminId;
    private String email;
    private String name;
    private String role;
}
```

User Service ke JwtResponseDTO se fark: `userId` ki jagah `adminId` field hai. Baaki sab same. Token mein `ROLE_ADMIN` aata hai jo Gateway aur Frontend role-based routing ke liye use karta hai.
