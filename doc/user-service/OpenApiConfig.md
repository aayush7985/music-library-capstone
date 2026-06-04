# OpenApiConfig.java (User Service)

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/config/OpenApiConfig.java`

## Kya Karta Hai Yeh File?

Yeh **Swagger UI** (OpenAPI documentation) ki configuration hai. Yeh automatically API documentation generate karta hai aur JWT authentication support add karta hai Swagger mein.

---

## Code

```java
@Configuration
@OpenAPIDefinition(info = @Info(
    title = "User Service API",
    version = "1.0",
    description = "Music Library - User Service"
))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {
}
```

---

## Explanation (Hinglish)

### `@OpenAPIDefinition`
Swagger UI ke top par dikhai dene wali information:
- `title` — API ka naam: "User Service API"
- `version` — "1.0"
- `description` — Short description

### `@SecurityScheme`
Swagger UI mein **Authorize** button add karta hai jahan JWT token daal sakte ho:
- `name = "bearerAuth"` — Yeh naam Controller mein `@SecurityRequirement(name = "bearerAuth")` se match karta hai.
- `scheme = "bearer"` — Bearer token type.
- `bearerFormat = "JWT"` — Sirf documentation ke liye.

---

## Swagger UI Kaise Kaam Karta Hai?

1. `http://localhost:8081/swagger-ui.html` kholo
2. "Authorize" button par click karo
3. JWT token daalo: `Bearer eyJhbGci...`
4. Ab saare protected endpoints test kar sakte ho

Yeh bahut useful hai development aur testing ke time — Postman ki zaroorat nahi!
