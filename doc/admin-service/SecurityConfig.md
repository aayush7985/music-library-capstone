# SecurityConfig.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/config/SecurityConfig.java`

## Kya Karta Hai?

Admin Service ki Spring Security configuration. BCryptPasswordEncoder bean define karta hai aur security rules set karta hai.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Password hashing ke liye
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Gateway JWT validate karta hai
            )
            .headers(h -> h.frameOptions(f -> f.disable()));
        return http.build();
    }
}
```

`anyRequest().permitAll()` — Kyunki API Gateway pehle JWT validate kar chuka hota hai, Admin Service ko dobara validate karne ki zaroorat nahi. BCryptPasswordEncoder bean zaroori hai passwords check karne ke liye.
