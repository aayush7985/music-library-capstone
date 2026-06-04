# AdminLoginDTO.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/dto/AdminLoginDTO.java`

## Kya Karta Hai Yeh File?

Admin login request ka data carry karne wala simple DTO.

---

## Code

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminLoginDTO {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
}
```

---

## Usage

```
POST /api/admin/login
{
  "email": "admin@musiclibrary.com",
  "password": "admin123"
}
```

UserLoginDTO se bilkul same structure — sirf naam alag hai. Alag class rakhne ki wajah: agar future mein admin login ke liye extra fields chahiye (jaise 2FA code) toh easily add ho sakta hai bina User Login affect kiye.
