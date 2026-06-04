# AdminServiceApplication.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/AdminServiceApplication.java`

## Kya Karta Hai Yeh File?

Yeh **Admin Service** ka main entry point hai. Is service ka kaam hai admin login, admin ka JWT generate karna, aur **User Service se data fetch karke** users manage karna (inter-service communication).

---

## Code

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }
}
```

| Annotation | Matlab |
|---|---|
| `@SpringBootApplication` | Spring Boot app start karo. |
| `@EnableDiscoveryClient` | Eureka mein register karo. |
| `@EnableFeignClients` | **OpenFeign** enable karo — taaki Admin Service User Service ko HTTP calls kar sake. |

---

## `@EnableFeignClients` Kyun?

Admin service users manage karta hai, lekin **users ka data User Service ke paas hai**. Toh admin service Feign client use karke User Service ko REST calls karti hai — yahi **inter-service communication** hai.

```
Admin requests users list
         ↓
AdminService → UserServiceClient (Feign)
         ↓
User Service (8081) → returns users
         ↓
Admin Service → Admin ko data de do
```

## Port: **8082**
## Database: `jdbc:h2:mem:admin_db` (sirf admin accounts ke liye)
