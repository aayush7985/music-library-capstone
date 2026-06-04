# UserLoginDTO.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/dto/UserLoginDTO.java`

## Kya Karta Hai Yeh File?

Yeh DTO user ke **login request** ka data carry karta hai. Jab user login karta hai, toh sirf email aur password chahiye — baaki sab unnecessary hai.

---

## Code Explanation (Hinglish)

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserLoginDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
```

### Fields

| Field | Validation | Purpose |
|---|---|---|
| `email` | `@NotBlank` + `@Email` | User ki identity — database mein search karne ke liye |
| `password` | `@NotBlank` | User verify karne ke liye (BCrypt match hoga) |

---

## Login Ka Pura Flow

```
POST /api/users/login
Body: { "email": "user@gmail.com", "password": "pass123" }
          ↓
UserLoginDTO mein data aaya
          ↓
UserService.login(dto) call hua
          ↓
Email se User dhundha database mein
          ↓
BCrypt.matches(inputPassword, storedHash) check hua
          ↓ Match hua? Haan ✓
JWT Token generate hua
          ↓
JwtResponseDTO return hua jisme token, userId, name sab hai
```

---

## Kyun Sirf 2 Fields?

Login ke liye sirf **email + password** kaafi hai. Baki sab information (naam, phone) database mein pehle se hai — login ke time lene ki zaroorat nahi.

Agar galat password diya — `InvalidCredentialsException` throw hoti hai aur **401 Unauthorized** return hota hai.
