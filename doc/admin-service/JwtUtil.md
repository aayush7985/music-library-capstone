# JwtUtil.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/util/JwtUtil.java`

## Kya Karta Hai?

Admin ke liye JWT token generate karta hai — User Service ke JwtUtil se bilkul same logic, sirf `ROLE_ADMIN` aata hai.

```java
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(Long adminId, String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claims(Map.of("userId", adminId, "role", role))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
}
```

**Kyun alag class hai?** Microservices principle: har service independent hona chahiye. Agar ek shared library use karte toh services tightly coupled ho jaati. Same secret key Config Server se aati hai isliye Gateway dono ke tokens validate kar sakta hai.
