# UserService.java (Interface)

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/service/UserService.java`

## Kya Karta Hai Yeh File?

Yeh ek **Service Interface** hai — sirf method signatures define karti hai, implementation nahi. Yeh **contract** ki tarah kaam karta hai.

---

## Code

```java
public interface UserService {
    UserResponseDTO register(UserRegistrationDTO dto);
    JwtResponseDTO login(UserLoginDTO dto);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UpdateUserDTO dto);
    void deleteUser(Long id);
    void disableUser(Long id);
    void enableUser(Long id);
}
```

---

## Interface Kyun Use Karte Hain?

**Loose coupling** ke liye — Controller ko yeh nahi pata ki implementation kaisi hai. Kal agar implementation badalni ho, controller ka code change nahi karna padega.

```
Controller → UserService (interface)
                    ↑
             UserServiceImpl (actual code yahan hai)
```

Spring `@Autowired` ya `@RequiredArgsConstructor` se automatically implementation inject karta hai.

---

## Methods Ka Summary

| Method | Kya Karta Hai |
|---|---|
| `register(dto)` | Naya user create karo, email duplicate check karo |
| `login(dto)` | Credentials verify karo, JWT token return karo |
| `getUserById(id)` | Ek user ki details lo |
| `getAllUsers()` | Saare users ki list (admin use) |
| `updateUser(id, dto)` | Profile update karo |
| `deleteUser(id)` | User delete karo |
| `disableUser(id)` | User ka `enabled = false` karo |
| `enableUser(id)` | User ka `enabled = true` karo |
