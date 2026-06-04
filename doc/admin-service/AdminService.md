# AdminService.java (Interface)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/service/AdminService.java`

## Kya Karta Hai?

Admin Service ka contract define karta hai — kya kya operations possible hain.

```java
public interface AdminService {
    JwtResponseDTO login(AdminLoginDTO dto);
    AdminResponseDTO getAdminById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UpdateUserDTO dto);
    void deleteUser(Long id);
    void disableUser(Long id);
    void enableUser(Long id);
}
```

**Note:** `getAllUsers()`, `updateUser()`, `deleteUser()` etc. admin ke liye hain **lekin yeh data User Service se Feign ke through aata hai** — Admin Service khud users store nahi karti. Yeh clean separation of concerns hai microservices architecture mein.
