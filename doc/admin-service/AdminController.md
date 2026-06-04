# AdminController.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/controller/AdminController.java`

## Kya Karta Hai Yeh File?

Admin ke saare REST endpoints yahan defined hain — admin login, aur user management operations.

---

## Code Explanation (Hinglish)

```java
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin API", description = "Admin login and user management")
public class AdminController {
```

Saare endpoints `/api/admin/...` se start honge.

---

### Endpoints Table

| Method | URL | Kya Karta Hai |
|---|---|---|
| POST | `/api/admin/login` | Admin login, JWT return |
| POST | `/api/admin/logout` | Logout confirmation |
| GET | `/api/admin/profile/{id}` | Admin apni profile dekhe |
| GET | `/api/admin/users` | Saare registered users dekho |
| GET | `/api/admin/users/{id}` | Ek specific user dekho |
| PUT | `/api/admin/users/{id}` | User ka data update karo |
| DELETE | `/api/admin/users/{id}` | User permanently delete karo |
| PATCH | `/api/admin/users/{id}/disable` | User ko block karo (song nahi dikhe) |
| PATCH | `/api/admin/users/{id}/enable` | Blocked user ko unblock karo |

---

### Disable User — Special Functionality
```java
@PatchMapping("/users/{id}/disable")
@Operation(summary = "Disable user (restrict song visibility for user)")
public ResponseEntity<Map<String, String>> disableUser(@PathVariable Long id) {
    adminService.disableUser(id);
    return ResponseEntity.ok(Map.of("message", "User disabled successfully"));
}
```

Jab admin user ko disable karta hai:
- User ka `enabled = false` ho jaata hai User Service mein.
- Disabled user login karne ki koshish kare toh `"Account is disabled"` error milta hai.
- Yahi **visibility restriction** feature hai project requirements mein.

---

### `@SecurityRequirement` Annotation

```java
@Operation(summary = "Get all users", security = @SecurityRequirement(name = "bearerAuth"))
```

Swagger UI mein yeh endpoints lock icon ke saath dikhenge — reminder ki JWT chahiye.
