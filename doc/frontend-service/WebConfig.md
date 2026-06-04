# WebConfig.java

## File Location
`frontend-service/src/main/java/com/musiclibrary/frontendservice/config/WebConfig.java`

## Kya Karta Hai?

Frontend Service ki Spring MVC configuration — CORS, static resources, aur root redirect.

---

## Code Explanation (Hinglish)

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }
}
```

---

### `addCorsMappings()` — CORS Configuration

CORS = Cross-Origin Resource Sharing

Frontend port 8086 par hai, API Gateway 8080 par. Browser by default **alag ports ko block karta hai** (same-origin policy).

CORS configuration se allow karte hain:
- `allowedOrigins("*")` — Kisi bhi origin se requests allowed
- `allowedMethods("GET", "POST", ...)` — Saare HTTP methods allowed
- `allowedHeaders("*")` — Saare headers allowed (Authorization JWT header bhi)

---

### `addResourceHandlers()` — Static Files

```java
registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/");
```

- `/**` — Koi bhi URL path
- `classpath:/static/` — `src/main/resources/static/` folder mein files dhoondho

Matlab: `/user-login.html` request aaye → `static/user-login.html` serve karo.

---

### `addViewControllers()` — Root Redirect

```java
registry.addRedirectViewController("/", "/index.html");
```

`http://localhost:8086/` → automatically `http://localhost:8086/index.html` par redirect ho jaata hai.
Matlab browser mein sirf `localhost:8086` type karo — landing page khul jaayega.

---

## `WebMvcConfigurer` Interface

Yeh interface Spring MVC ki default settings ko **customize** karne deta hai:
- CORS rules
- Static resource locations
- View controllers
- Interceptors
- Message converters

`implements WebMvcConfigurer` karne ke baad sirf woh methods override karo jo customize karne hain — baaki Spring default handle karta hai.
