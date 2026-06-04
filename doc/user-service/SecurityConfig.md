# SecurityConfig.java (User Service)

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/config/SecurityConfig.java`

## Kya Karta Hai Yeh File?

Yeh Spring Security ki **configuration** hai. Yeh define karta hai ki kaun si URLs publicly accessible hain aur security kaise kaam karegi.

---

## Code Explanation (Hinglish)

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
```

- `@Configuration` — Yeh ek configuration class hai.
- `@EnableWebSecurity` — Spring Security enable karo.

---

### PasswordEncoder Bean
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

- **BCrypt** — Industry standard password hashing algorithm.
- Yeh `@Bean` poori application mein inject hoti hai (UserServiceImpl mein `passwordEncoder.encode()` yahi use karta hai).
- BCrypt har baar alag salt use karta hai isliye same password ka hash alag hota hai — very secure!

---

### Security Filter Chain
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/api/users/register",
                "/api/users/login",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/h2-console/**",
                "/actuator/**"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .headers(headers -> headers.frameOptions(frame -> frame.disable()));
    return http.build();
}
```

**Line by line:**

| Setting | Matlab |
|---|---|
| `csrf.disable()` | CSRF protection OFF karo — REST APIs ke liye zaroorat nahi (stateless hain). |
| `STATELESS` | Server koi session store nahi karta — har request apne JWT ke saath self-contained hai. |
| `permitAll()` | Yeh paths bina login ke accessible hain. |
| `anyRequest().authenticated()` | Baaki sab ke liye authentication required — lekin Gateway already JWT validate karta hai. |
| `frameOptions.disable()` | H2 Console iframe mein kaam kare isliye. |

---

## Note: Kya Gateway Enough Hai?

Hamare project mein **Gateway JWT validate karta hai** aur downstream services ko trust karta hai. Service level par bhi Spring Security hai lekin authentication filter nahi lagaya — Gateway hi primary security layer hai.

`BCryptPasswordEncoder` bean zaroori hai kyunki passwords hash karke store karne chahiye.
