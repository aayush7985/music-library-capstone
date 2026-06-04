# EmailAlreadyExistsException.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/exception/EmailAlreadyExistsException.java`

## Kya Karta Hai Yeh File?

Yeh custom exception tab throw hoti hai jab koi user **pehle se registered email** se dobara register karne ki koshish kare.

---

## Code

```java
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email already registered: " + email);
    }
}
```

---

## Kahan Use Hoti Hai?

```java
// UserServiceImpl.register() mein:
if (userRepository.existsByEmail(dto.getEmail())) {
    throw new EmailAlreadyExistsException(dto.getEmail());
}
```

**Response:**
```json
{
  "status": 409,
  "message": "Email already registered: aayush@gmail.com",
  "timestamp": "2024-01-15T10:30:00"
}
```

HTTP Status: **409 Conflict** — resource already exists.
