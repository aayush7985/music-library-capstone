# UserResponseDTO.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/dto/UserResponseDTO.java`

## Kya Karta Hai Yeh File?

Yeh DTO **response** ke liye hai — yaani jab service user data return karti hai toh is DTO ke roop mein karta hai. Entity seedha return karne ke bajaye yeh safe wrapper hai.

---

## Code Explanation (Hinglish)

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

---

## Kyun Response DTO Use Karte Hain?

**Sabse important reason — PASSWORD HIDE KARNA!**

```
User Entity mein hai:  id, name, email, phone, PASSWORD, role, enabled, createdAt
                                                    ↑
                                          Yeh KABHI response mein nahi aana chahiye!

UserResponseDTO mein hai: id, name, email, phone, role, enabled, createdAt
                                                 ↑
                                        Password nahi hai — SAFE!
```

Agar Entity seedha return karte toh hashed password bhi JSON mein aa jaata — yeh ek **security vulnerability** hoti.

---

## Fields Ka Matlab

| Field | Matlab |
|---|---|
| `id` | User ka unique database ID |
| `name` | User ka naam |
| `email` | Email address |
| `phoneNumber` | Phone number |
| `role` | "ROLE_USER" — access level |
| `enabled` | `true` = active, `false` = blocked by admin |
| `createdAt` | Kab register kiya |

---

## Entity Se DTO Mein Convert Karna (UserServiceImpl mein)

```java
private UserResponseDTO toResponseDTO(User user) {
    return UserResponseDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .role(user.getRole().name())
            .enabled(user.isEnabled())
            .createdAt(user.getCreatedAt())
            .build();
}
```

Password field intentionally **skip** kiya gaya — yahi sabse important design decision hai.
