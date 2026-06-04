# ApiGatewayApplication.java

## File Location
`api-gateway/src/main/java/com/musiclibrary/apigateway/ApiGatewayApplication.java`

## Kya Karta Hai Yeh File?

Yeh **API Gateway** ka main entry point hai. API Gateway poore application ka **ek hi darwaza** (single entry point) hai — frontend se aane wali sabhi requests pehle yahan aati hain, phir yeh unhe sahi microservice tak bhejta hai.

---

## Code Explanation (Hinglish)

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

### Annotations Ka Matlab

| Annotation | Matlab |
|---|---|
| `@SpringBootApplication` | Standard Spring Boot application start karo. |
| `@EnableDiscoveryClient` | Eureka Server mein apne aap ko register karo taaki doosre services jaanein ki gateway kahan hai. |

---

## API Gateway Kya Karta Hai?

**Security Guard ki tarah socho:**
- Har visitor (request) pehle security guard (API Gateway) ke paas aata hai.
- Guard check karta hai — **"kya tumhare paas valid JWT token hai?"**
- Agar haan — request ko andar jaane deta hai sahi department mein.
- Agar nahi — **401 Unauthorized** return karta hai.

### Routing Example:
```
Browser request: GET /api/songs
       ↓
API Gateway (8080)
       ↓ JWT valid? Haan ✓
       ↓
Song Service (8083) ko forward
       ↓
Response wapas browser ko
```

---

## Is Service Mein Kya Kya Hai?

1. **`JwtAuthFilter`** — JWT token validate karta hai
2. **`JwtUtil`** — Token ko decode karne ki utility
3. **Static Files** — Frontend HTML/CSS/JS files (api-gateway ke resources mein bhi hain)

---

## Port
- **8080** — Yeh sabse important port hai. Frontend aur backend dono ke liye yahi gateway hai.
