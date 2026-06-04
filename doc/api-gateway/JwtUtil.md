# JwtUtil.java (API Gateway)

## File Location
`api-gateway/src/main/java/com/musiclibrary/apigateway/util/JwtUtil.java`

## Kya Karta Hai Yeh File?

Yeh class JWT (JSON Web Token) ko **read aur validate** karne ki utility provide karti hai. Gateway mein yeh sirf token **verify** karta hai — generate nahi karta (generation User/Admin Service mein hota hai).

---

## Code Explanation (Hinglish)

### Signing Key Banana
```java
@Value("${jwt.secret}")
private String secret;

private SecretKey getSigningKey() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
}
```
- `@Value("${jwt.secret}")` — Config Server se JWT secret key aati hai.
- `Keys.hmacShaKeyFor()` — Secret string ko ek **cryptographic key** mein convert karta hai jo token verify karne ke kaam aati hai.

---

### Token Se Data Nikalna
```java
public Claims extractAllClaims(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
```
- **Claims** — Token ke andar stored data hota hai (email, role, userId, expiry etc.)
- `Jwts.parser()` — JWT library ka parser use karo.
- `verifyWith(key)` — Is key se signature verify karo.
- `parseSignedClaims(token)` — Token ko decode karo.
- Agar token fake hai ya tampered hai — Exception throw hogi.

---

### Token Validate Karna
```java
public boolean validateToken(String token) {
    try {
        extractAllClaims(token);
        return !isTokenExpired(token);
    } catch (Exception e) {
        return false;  // Koi bhi error = invalid token
    }
}
```
- Pehle claims extract karne ki koshish karo.
- Agar success — expiry check karo.
- Agar koi bhi exception aaye (fake token, tampered, expired) — `false` return karo.

---

### Helper Methods
```java
public boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
}

public String extractRole(String token) {
    return extractAllClaims(token).get("role", String.class);
}

public String extractEmail(String token) {
    return extractAllClaims(token).getSubject();
}

public String extractUserId(String token) {
    Object userId = extractAllClaims(token).get("userId");
    return userId != null ? userId.toString() : null;
}
```

| Method | Kya Karta Hai |
|---|---|
| `isTokenExpired()` | Token ki expiry date current time se pehle hai? Haan = expired |
| `extractRole()` | Token se user ka role nikalo (ROLE_USER ya ROLE_ADMIN) |
| `extractEmail()` | Token ka subject = user ka email |
| `extractUserId()` | Token se userId nikalo |

---

## JWT Token Structure

JWT token 3 parts ka hota hai separated by `.`:
```
Header.Payload.Signature
```

**Payload mein kya hota hai hamare token mein:**
```json
{
  "sub": "user@example.com",
  "userId": 1,
  "role": "ROLE_USER",
  "iat": 1700000000,
  "exp": 1700086400
}
```

Yahi data `extractAllClaims()` return karta hai.
