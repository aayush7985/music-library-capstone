# SecurityConfig.java (Song Service)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/config/SecurityConfig.java`

## Kya Karta Hai?

Song Service ki security configuration — saari requests allow karta hai (Gateway JWT validate kar chuka hota hai).

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // Gateway already checked JWT
        )
        .headers(h -> h.frameOptions(f -> f.disable()));
    return http.build();
}
```

Song Service aur baaki downstream services `permitAll()` use karte hain. Security responsibility **API Gateway** ki hai jo pehle hi JWT validate kar chuka hota hai.
