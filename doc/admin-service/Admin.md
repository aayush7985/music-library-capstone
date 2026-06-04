# Admin.java (Entity)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/entity/Admin.java`

## Kya Karta Hai Yeh File?

Yeh JPA Entity hai jo `admins` table represent karti hai. Admins ka data **alag database** mein store hota hai — User database se bilkul alag.

---

## Code Explanation (Hinglish)

```java
@Entity
@Table(name = "admins")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_ADMIN;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum Role {
        ROLE_ADMIN
    }
}
```

---

## User Entity Se Kya Fark Hai?

| Field | User Entity | Admin Entity |
|---|---|---|
| `role` | `ROLE_USER` | `ROLE_ADMIN` |
| `phoneNumber` | Hai ✓ | Nahi ✗ |
| `enabled` | Hai ✓ | Nahi ✗ |

- Admin ko phone number nahi chahiye — sirf email/password se login.
- Admin ko enable/disable nahi kiya ja sakta — woh permanent hote hain.
- **Alag database** — `admin_db` sirf admins ka data rakhta hai.

---

## DataInitializer Se Banega Default Admin

```java
// DataInitializer.java mein yeh run hoga:
Admin admin = Admin.builder()
    .name("Super Admin")
    .email("admin@musiclibrary.com")
    .password(passwordEncoder.encode("admin123"))
    .role(Admin.Role.ROLE_ADMIN)
    .build();
```

Application start hote hi ek default admin create ho jaata hai.
