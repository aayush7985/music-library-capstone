# OpenApiConfig.java (Song Service)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/config/OpenApiConfig.java`

## Kya Karta Hai?

Song Service ke liye Swagger UI configuration.

```java
@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Song Service API",
    version = "1.0",
    description = "Music Library - Song Service"
))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
    scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {}
```

`http://localhost:8083/swagger-ui.html` par Song Service ki documentation milegi. Admin wahan se directly API test kar sakta hai — song add karo, visibility toggle karo, search karo.
