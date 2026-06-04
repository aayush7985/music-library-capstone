# OpenApiConfig.java (Notification Service)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/config/OpenApiConfig.java`

## Kya Karta Hai?

Notification Service ke liye Swagger UI configuration.

```java
@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Notification Service API",
    version = "1.0",
    description = "Music Library - Notification Service"
))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
    scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {}
```

`http://localhost:8085/swagger-ui.html` par Notification Service ki documentation milegi. Testing ke time manually `/new-song` trigger kar sakte hain ya specific user ki notifications check kar sakte hain.
