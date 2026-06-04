# JwtAuthFilter.java

## File Location
`api-gateway/src/main/java/com/musiclibrary/apigateway/filter/JwtAuthFilter.java`

## Kya Karta Hai Yeh File?

Yeh ek **Global Filter** hai jo API Gateway mein har incoming request ko **intercept** karta hai aur JWT token check karta hai. Agar token valid nahi hai toh request ko block kar deta hai.

---

## Code Explanation (Hinglish)

### Class Declaration
```java
@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {
```
- `@Component` — Spring is class ko automatically detect karke bean banata hai.
- `GlobalFilter` — Yeh interface implement karne se yeh filter **har request par** automatically apply hota hai.
- `Ordered` — Yeh batata hai ki is filter ki priority kya hai (order = -1 yaani sabse pehle run karo).

---

### Public Paths (Bina Token Ke Allowed)
```java
private static final List<String> PUBLIC_PATHS = List.of(
    "/api/users/register",
    "/api/users/login",
    "/api/admin/login",
    "/swagger-ui",
    "/v3/api-docs",
    "/actuator"
);
```
- Yeh woh paths hain jinhe **bina token ke** access kiya ja sakta hai.
- Jaise login aur register — yahan token maangna galat hoga kyunki user ne abhi login hi nahi kiya!

---

### filter() Method — Main Logic
```java
public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String path = request.getURI().getPath();

    // 1. Agar public path hai toh seedha aage bhej do
    if (isPublicPath(path)) {
        return chain.filter(exchange);
    }

    // 2. Authorization header check karo
    String authHeader = request.getHeaders().getFirst("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return unauthorizedResponse(exchange);  // 401 return karo
    }

    // 3. Token nikalo aur validate karo
    String token = authHeader.substring(7);  // "Bearer " hata ke baaki lo
    if (!jwtUtil.validateToken(token)) {
        return unauthorizedResponse(exchange);
    }

    // 4. Token se user info nikalo aur headers mein daalo
    String email = jwtUtil.extractEmail(token);
    String role = jwtUtil.extractRole(token);
    String userId = jwtUtil.extractUserId(token);

    // 5. Mutated request aage bhejo
    ServerHttpRequest mutatedRequest = request.mutate()
        .header("X-Auth-User-Email", email)
        .header("X-Auth-User-Role", role)
        .header("X-Auth-User-Id", userId)
        .build();

    return chain.filter(exchange.mutate().request(mutatedRequest).build());
}
```

**Step by step process:**
1. **Public path check** — Login/Register? Seedha jaane do.
2. **Header check** — `Authorization: Bearer <token>` format mein hai?
3. **Token validate** — Token expired toh nahi? Genuine hai?
4. **Info extract** — Token se email, role, userId nikalo.
5. **Headers add karo** — Downstream services ko batao ki yeh user kaun hai.

---

### Unauthorized Response
```java
private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
    response.setStatusCode(HttpStatus.UNAUTHORIZED);  // 401 status
    return response.setComplete();
}
```
- Jab token nahi hai ya invalid hai — **HTTP 401 Unauthorized** return karo.

---

### `getOrder()` Method
```java
public int getOrder() {
    return -1;  // Negative value = highest priority
}
```
- `-1` matlab yeh filter **sabse pehle** run karta hai — koi bhi request bina check ke aage nahi ja sakti.

---

## Flow Summary
```
Request aaya
    ↓
Public path? → Haan → Seedha forward
    ↓ Nahi
Bearer token hai? → Nahi → 401 Return
    ↓ Haan
Token valid? → Nahi → 401 Return
    ↓ Haan
User info headers mein daalo
    ↓
Downstream service ko forward karo
```
