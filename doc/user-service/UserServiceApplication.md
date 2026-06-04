# UserServiceApplication.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/UserServiceApplication.java`

## Kya Karta Hai Yeh File?

Yeh **User Service** ka main entry point hai. Is service ka kaam hai users ka **registration, login, aur profile management** handle karna.

---

## Code Explanation

```java
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

| Annotation | Matlab |
|---|---|
| `@SpringBootApplication` | Spring Boot application start karo — auto-configuration, component scan sab ho jaata hai. |
| `@EnableDiscoveryClient` | Eureka Server mein register karo taaki gateway aur doosri services ise dhundh sakein. |

---

## Is Service Ki Responsibilities

- Naye users ka **registration**
- Existing users ka **login** (JWT token generate karta hai)
- User **profile CRUD** (Create, Read, Update, Delete)
- User enable/disable karna (admin ke liye)
- Password **BCrypt** se encrypt karke store karna

## Port: **8081**
## Database: H2 in-memory — `jdbc:h2:mem:user_db`
