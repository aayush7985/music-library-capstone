# FrontendServiceApplication.java

## File Location
`frontend-service/src/main/java/com/musiclibrary/frontendservice/FrontendServiceApplication.java`

## Kya Karta Hai?

**Dedicated Frontend Microservice** ka entry point. Yeh Spring Boot service sirf **HTML, CSS, JS files serve** karti hai — koi backend logic nahi.

---

## Code

```java
@SpringBootApplication
@EnableDiscoveryClient
public class FrontendServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendServiceApplication.class, args);
    }
}
```

---

## Kyun Alag Service?

Microservices architecture mein **separation of concerns** bahut important hai:
- Frontend Service → UI serve karo (port 8086)
- API Gateway → API route karo (port 8080)
- Business Services → Logic handle karo (8081-8085)

Agar sab ek saath hote toh ek single point of failure hota. Ab frontend service crash ho toh API working rahega (aur vice versa).

---

## Port: **8086**

## Folder Structure
```
frontend-service/src/main/resources/static/
├── index.html              ← Landing page
├── user-login.html
├── user-register.html
├── admin-login.html
├── user-dashboard.html     ← Songs browse
├── song-detail.html
├── playlists.html
├── playlist-detail.html    ← Player controls
├── profile.html
├── admin-dashboard.html
├── admin-songs.html
├── admin-users.html
├── css/styles.css          ← Dark theme
└── js/api.js               ← API utility
```

Spring Boot `src/main/resources/static/` folder mein rakhi files automatically serve hoti hain.

## API Calls Kahan Jaati Hain?

```javascript
const API_BASE = 'http://localhost:8080';
```

Frontend service port 8086 par serve hoti hai, lekin saari API calls **API Gateway (8080)** ko jaati hain. Gateway JWT validate karke sahi microservice ko forward karta hai.
