# InvalidCredentialsException.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/exception/InvalidCredentialsException.java`

## Kya Karta Hai Yeh File?

Yeh exception tab throw hoti hai jab user **galat email ya password** se login karne ki koshish kare.

---

## Code

```java
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}
```

---

## Security Best Practice — Ek Hi Message

Notice karo ki message generic hai: `"Invalid email or password"` — yeh specifically nahi batata ki email galat hai ya password.

**Kyun?**
- Agar "Email not found" bolta — hacker ko pata chal jaata ki yeh email registered nahi.
- Agar "Wrong password" bolta — hacker ko pata chal jaata ki email sahi hai, sirf password try karna hai.
- Generic message se **brute force attacks** mushkil ho jaate hain.

---

## Kahan Use Hoti Hai?

```java
// UserServiceImpl.login() mein:
User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(InvalidCredentialsException::new);  // Email nahi mila

if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
    throw new InvalidCredentialsException();  // Password galat
}
```

**HTTP Response:** `401 Unauthorized`
