# AdminServiceImpl.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/service/impl/AdminServiceImpl.java`

## Kya Karta Hai Yeh File?

Admin Service ka main business logic yahan hai. Admin login karna aur **User Service ko Feign calls karke** users manage karna — yahi is class ka kaam hai.

---

## Code Explanation (Hinglish)

### Dependencies
```java
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserServiceClient userServiceClient;  // Feign client!
```

`UserServiceClient` inject hua hai — ab yeh Feign se User Service ko call kar sakta hai.

---

### login() Method
```java
public JwtResponseDTO login(AdminLoginDTO dto) {
    Admin admin = adminRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new AdminNotFoundException("Invalid credentials"));

    if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
        throw new AdminNotFoundException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(admin.getId(), admin.getEmail(), admin.getRole().name());

    return JwtResponseDTO.builder()
            .token(token)
            .adminId(admin.getId())
            .email(admin.getEmail())
            .name(admin.getName())
            .role(admin.getRole().name())
            .build();
}
```

User login se almost same — difference sirf yeh ki `adminRepository` use hoti hai aur role `ROLE_ADMIN` aata hai JWT mein.

---

### User Management (Feign ke through)
```java
public List<UserResponseDTO> getAllUsers() {
    return userServiceClient.getAllUsers();  // Feign magic!
}

public UserResponseDTO updateUser(Long id, UpdateUserDTO dto) {
    return userServiceClient.updateUser(id, dto);
}

public void deleteUser(Long id) {
    userServiceClient.deleteUser(id);
}

public void disableUser(Long id) {
    userServiceClient.disableUser(id);  // User ban karna
}
```

**Notice:** Admin Service ke paas users ka koi data nahi hai. Woh sirf **proxy** ki tarah kaam karta hai — admin ka request leta hai, User Service ko forward karta hai, response wapas deta hai.

---

## Microservice Communication Pattern

```
Admin → DELETE /api/admin/users/5
           ↓
    AdminController receives
           ↓
    adminService.deleteUser(5)
           ↓
    userServiceClient.deleteUser(5)  [Feign]
           ↓
    User Service: DELETE /api/users/5
           ↓
    User deleted, response wapas
```

Yeh **synchronous inter-service communication** hai — ek service dusri service ko directly call karta hai.
