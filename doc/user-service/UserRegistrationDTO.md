# UserRegistrationDTO.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/dto/UserRegistrationDTO.java`

## Kya Hota Hai DTO?

**DTO = Data Transfer Object**

Yeh ek simple class hoti hai jo sirf **data carry** karti hai — koi business logic nahi hoti. Jab frontend se data aata hai (JSON), toh woh pehle DTO mein convert hota hai, phir service layer entity mein convert karti hai.

**Kyun use karte hain?**
- Entity class seedha expose karna dangerous hota hai (password aur sensitive fields leak ho sakte hain).
- DTO sirf woh data leta hai jo actually chahiye.

---

## Code Explanation (Hinglish)

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRegistrationDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    private String password;
}
```

### Validations Ka Matlab

| Field | Validation | Agar Fail Ho |
|---|---|---|
| `name` | `@NotBlank` | "Name is required" error |
| `email` | `@Email` | "Invalid email format" error |
| `phoneNumber` | `@Pattern` (10 digits) | "Phone must be 10 digits" error |
| `password` | `@NotBlank` | "Password is required" error |

---

## Flow

```
Frontend se JSON aaya:
{
  "name": "Aayush",
  "email": "aayush@gmail.com",
  "phoneNumber": "9876543210",
  "password": "mypassword123"
}
          ↓
UserRegistrationDTO mein map hua
          ↓
Validations check hui (@Valid annotation se)
          ↓
UserService.register(dto) call hua
          ↓
Password BCrypt se encrypt hua
          ↓
User entity bani aur database mein save hua
```

---

## Note
- Is DTO mein **role** field nahi hai — kyunki naye user ka role hamesha `ROLE_USER` hota hai, user khud choose nahi kar sakta.
- **id** aur **createdAt** bhi nahi hain — yeh database khud generate karta hai.
