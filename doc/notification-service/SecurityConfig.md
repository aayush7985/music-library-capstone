# SecurityConfig.java (Notification Service)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/config/SecurityConfig.java`

## Kya Karta Hai?

Notification Service ki security — `permitAll()` Gateway trust approach.

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()
        )
        .headers(h -> h.frameOptions(f -> f.disable()));
    return http.build();
}
```

Yeh same pattern hai jaise Song Service aur Playlist Service mein. Gateway JWT validate karta hai — downstream services trust karte hain.
