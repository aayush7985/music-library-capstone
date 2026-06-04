# OpenApiConfig.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/config/OpenApiConfig.java`

## Kya Karta Hai?

Admin Service ke liye Swagger UI configuration — alag title aur JWT auth support.

```java
@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Admin Service API",
    version = "1.0",
    description = "Music Library - Admin Service"
))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
    scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {}
```

`http://localhost:8082/swagger-ui.html` par Admin Service ki API documentation milegi — sirf admin-specific endpoints. Har microservice ka **apna alag Swagger** hota hai — yeh requirement mein bhi tha.
