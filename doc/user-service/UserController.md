# UserController.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/controller/UserController.java`

## Kya Karta Hai Yeh File?

Yeh **REST Controller** hai — HTTP requests ko receive karta hai aur service ko call karke response return karta hai. Yeh presentation layer hai — koi business logic yahan nahi hoti.

---

## Code Explanation (Hinglish)

### Class Level
```java
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "User registration, login, and management")
public class UserController {
```

| Annotation | Matlab |
|---|---|
| `@RestController` | Yeh REST API controller hai — JSON response automatically return hota hai. |
| `@RequestMapping("/api/users")` | Saare endpoints `/api/users/...` se start honge. |
| `@Tag` | Swagger UI mein is controller ka naam aur description. |

---

### Register Endpoint
```java
@PostMapping("/register")
@Operation(summary = "Register a new user")
public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(dto));
}
```
- `@PostMapping` — POST method handle karta hai.
- `@Valid` — Request body par validations chalao (@NotBlank, @Email etc.)
- `HttpStatus.CREATED` — **201 Created** return karo (user bana toh 200 nahi, 201 proper hai).

---

### Login Endpoint
```java
@PostMapping("/login")
public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
    return ResponseEntity.ok(userService.login(dto));
}
```
- `ResponseEntity.ok()` — **200 OK** with body.

---

### Logout Endpoint
```java
@PostMapping("/logout")
public ResponseEntity<Map<String, String>> logout() {
    return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
}
```
- **JWT stateless hai** — server par kuch store nahi hota. Logout sirf frontend ka kaam hai (localStorage se token delete karo).
- Yeh endpoint sirf confirmation message deta hai.

---

### Get/Update/Delete Endpoints
```java
@GetMapping("/{id}")
public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
}

@PutMapping("/{id}")
public ResponseEntity<UserResponseDTO> updateUser(
        @PathVariable Long id, @Valid @RequestBody UpdateUserDTO dto) {
    return ResponseEntity.ok(userService.updateUser(id, dto));
}

@DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
}
```

| HTTP Method | Endpoint | Kya Karta Hai |
|---|---|---|
| POST | `/api/users/register` | Naya user banao |
| POST | `/api/users/login` | Login karo, JWT lo |
| POST | `/api/users/logout` | Logout (client-side) |
| GET | `/api/users/{id}` | User details lo |
| GET | `/api/users` | Saare users lo |
| PUT | `/api/users/{id}` | Profile update karo |
| DELETE | `/api/users/{id}` | User delete karo |
| PATCH | `/api/users/{id}/disable` | User block karo |
| PATCH | `/api/users/{id}/enable` | User unblock karo |

---

## `@PathVariable` vs `@RequestBody`

- `@PathVariable Long id` — URL se value lo: `/api/users/5` → id = 5
- `@RequestBody UpdateUserDTO dto` — Request body ka JSON DTO mein convert karo
- `@Valid` — Automatically validation run karo
