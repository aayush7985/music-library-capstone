# DataInitializer.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/config/DataInitializer.java`

## Kya Karta Hai Yeh File?

Yeh class application **start hote hi** ek **default admin account** create karti hai agar koi admin exist nahi karta. Yeh ek seed data setup hai.

---

## Code Explanation (Hinglish)

```java
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!adminRepository.existsByEmail("admin@musiclibrary.com")) {
            Admin admin = Admin.builder()
                    .name("Super Admin")
                    .email("admin@musiclibrary.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Admin.Role.ROLE_ADMIN)
                    .build();
            adminRepository.save(admin);
            System.out.println("Default admin created: admin@musiclibrary.com / admin123");
        }
    }
}
```

---

## `CommandLineRunner` Interface

- Spring Boot mein yeh interface implement karne se `run()` method **application startup ke baad** automatically call hota hai.
- Ek hi baar run hota hai — application start par.

---

## Flow

```
Application start hua
        ↓
DataInitializer.run() call hua
        ↓
"admin@musiclibrary.com" already hai?
        ↓ Nahi
Admin object banao (password BCrypt se hash karke)
        ↓
Database mein save karo
        ↓
Console par print: "Default admin created..."
```

---

## Kyun Zaroori Hai?

H2 **in-memory database** use kar rahe hain — har baar application restart hone par data **wipe** ho jaata hai. Isliye DataInitializer se har restart par admin automatically ban jaata hai.

**Default Credentials:**
- Email: `admin@musiclibrary.com`
- Password: `admin123`
