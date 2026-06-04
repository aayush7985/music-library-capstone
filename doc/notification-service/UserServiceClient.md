# UserServiceClient.java (Notification Service)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/client/UserServiceClient.java`

## Kya Karta Hai?

Notification Service ko User Service se saare users ki list laane deta hai (notifications create karne ke liye).

```java
@FeignClient(name = "user-service", path = "/api/users")
public interface UserServiceClient {

    @GetMapping
    List<UserResponseDTO> getAllUsers();
}
```

Sirf ek method — `GET /api/users` — User Service se saare users la. Feign automatically Eureka se User Service ka address dhundh ke call karta hai.

---

## Flow

```
createNotificationsForAllUsers() call hua
         ↓
userServiceClient.getAllUsers() [Feign call]
         ↓
User Service: SELECT * FROM users (all users return)
         ↓
List<UserResponseDTO> wapas aayi
         ↓
Har user ke liye Notification entry banao
```
