# JwtUtil.java (User Service)

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/util/JwtUtil.java`

## Kya Karta Hai Yeh File?

Yeh class JWT token **generate** karti hai jab user successfully login karta hai. (API Gateway wala JwtUtil sirf validate karta tha, yeh wala actually **token banata hai**.)

---

## Code Explanation (Hinglish)

```java
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;
```

- `@Value` — Config Server se values inject hoti hain.
- `secret` — Token sign karne ki key (sab services mein same honi chahiye).
- `expiration` — `86400000` milliseconds = **24 ghante** — token itne time baad expire hoga.

---

### Token Generate Karna

```java
public String generateToken(Long userId, String email, String role) {
    return Jwts.builder()
            .subject(email)                           // "sub" claim = email
            .claims(Map.of(
                "userId", userId,                     // Custom claim
                "role", role                          // Custom claim
            ))
            .issuedAt(new Date())                     // "iat" = aaj ka time
            .expiration(new Date(
                System.currentTimeMillis() + expiration  // "exp" = 24 ghante baad
            ))
            .signWith(getSigningKey())                // HMAC-SHA256 se sign karo
            .compact();                              // String mein convert karo
}
```

**Token ke andar kya hoga:**
```json
{
  "sub": "user@gmail.com",
  "userId": 1,
  "role": "ROLE_USER",
  "iat": 1700000000,
  "exp": 1700086400
}
```

---

### Signing Key

```java
private SecretKey getSigningKey() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
}
```

- Secret string ko bytes mein convert karo.
- `Keys.hmacShaKeyFor()` — HMAC-SHA256 algorithm ke liye key banao.
- Yahi key token sign karne mein aur validate karne mein use hoti hai.

---

## JWT Kaise Kaam Karta Hai?

```
Login success
     ↓
generateToken(userId, email, role) call hua
     ↓
Token bana: "eyJhbGci...header.eyJzdWIi...payload.signature"
     ↓
Frontend ko return hua
     ↓
Frontend ne localStorage mein save kiya
     ↓
Har request mein header mein bheja: "Authorization: Bearer <token>"
     ↓
API Gateway ne token validate kiya
     ↓
Request aage gayi
```

---

## Note

User Service aur Admin Service dono ke paas apna `JwtUtil` hai. Dono same **secret key** use karte hain (config-server se aati hai) — isliye gateway dono ke tokens validate kar sakta hai.
