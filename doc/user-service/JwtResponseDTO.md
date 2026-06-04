# JwtResponseDTO.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/dto/JwtResponseDTO.java`

## Kya Karta Hai Yeh File?

Yeh DTO **login ke baad** return hota hai. Isme JWT token ke saath user ki basic info hoti hai jo frontend localStorage mein save karta hai.

---

## Code Explanation (Hinglish)

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String email;
    private String name;
    private String role;
}
```

---

## Fields Ka Matlab

| Field | Default | Matlab |
|---|---|---|
| `token` | — | JWT token string — yeh frontend har request mein bhejega |
| `type` | `"Bearer"` | Token ka type — standard HTTP authentication scheme |
| `userId` | — | User ka ID — playlist, notifications ke liye chahiye |
| `email` | — | User ka email |
| `name` | — | User ka naam — dashboard mein display ke liye |
| `role` | — | `ROLE_USER` ya `ROLE_ADMIN` — kaunsa page dikhana hai decide karne ke liye |

---

## Login Response Ka Example (JSON)

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGdtYWlsLmNvbSJ9...",
  "type": "Bearer",
  "userId": 1,
  "email": "user@gmail.com",
  "name": "Aayush",
  "role": "ROLE_USER"
}
```

---

## Frontend Mein Kaise Use Hota Hai?

```javascript
// Login ke baad:
localStorage.setItem('token', res.data.token);
localStorage.setItem('user', JSON.stringify(res.data));

// Har API call mein:
headers['Authorization'] = `Bearer ${token}`;
```

**Bearer Token kya hota hai?**
HTTP standard ke according, authorization header aise bheja jaata hai:
```
Authorization: Bearer eyJhbGci...
```
Yahi header API Gateway check karta hai har request par.
