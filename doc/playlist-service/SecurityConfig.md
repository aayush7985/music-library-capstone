# SecurityConfig.java (Playlist Service)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/config/SecurityConfig.java`

## Kya Karta Hai?

Playlist Service ki security — Gateway already JWT validate karta hai, yahan `permitAll()`.

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

Same pattern as Song/Notification Service — Gateway JWT check karta hai, downstream services trust karte hain. `userId` `X-Auth-User-Id` header se verified manner mein aata hai.
