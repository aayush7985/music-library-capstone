# UpdateUserDTO.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/dto/UpdateUserDTO.java`

## Kya Karta Hai Yeh File?

Yeh DTO user ke **profile update** ke liye hai. Isme sirf woh fields hain jo user update kar sakta hai — email aur role change nahi ho sakta isliye woh yahan nahi hain.

---

## Code Explanation (Hinglish)

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateUserDTO {
    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    private String password;
}
```

---

## Fields Ka Matlab

| Field | Validation | Matlab |
|---|---|---|
| `name` | Optional (null allowed) | Naam badalna ho toh bhejo |
| `phoneNumber` | `@Pattern` — 10 digits | Phone number update karna |
| `password` | Optional (null allowed) | Password change karna ho toh |

---

## Partial Update (PATCH logic)

`UserServiceImpl` mein yeh logic hai:
```java
if (dto.getName() != null && !dto.getName().isBlank()) {
    user.setName(dto.getName());
}
if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank()) {
    user.setPhoneNumber(dto.getPhoneNumber());
}
if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
}
```

Matlab: **Sirf woh fields update honge jo request mein bheje gaye hain.** Agar sirf name bheja toh sirf name badle ga — phone aur password same rahenge.

---

## Email Update Kyun Nahi?

Email ek **unique identifier** hai — isko change karne se security issues ho sakte hain (duplicate emails, JWT token mein purana email hoga). Isliye email update functionality is project mein nahi hai.
