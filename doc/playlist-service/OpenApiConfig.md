# OpenApiConfig.java (Playlist Service)

## File Location
`playlist-service/src/main/java/com/musiclibrary/playlistservice/config/OpenApiConfig.java`

## Kya Karta Hai?

Playlist Service ke liye Swagger UI configuration.

```java
@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Playlist Service API",
    version = "1.0",
    description = "Music Library - Playlist Service"
))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
    scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {}
```

`http://localhost:8084/swagger-ui.html` par Playlist Service API documentation milegi. Testing ke time Swagger se directly playlist create, song add, search test kar sakte hain.
