# UserNotFoundException.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/exception/UserNotFoundException.java`

## Kya Karta Hai Yeh File?

Yeh ek **Custom Exception** class hai jo tab throw hoti hai jab koi user database mein nahi milta.

---

## Code Explanation (Hinglish)

```java
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}
```

### `extends RuntimeException`
- `RuntimeException` ko extend karne se yeh ek **unchecked exception** ban jaati hai.
- Matlab hume `throws` declaration nahi likhna padta — Java automatically handle karta hai.

### 2 Constructors
1. **String message** — Custom message de sakte ho: `new UserNotFoundException("Email not found")`
2. **Long id** — ID se automatic message banta hai: `"User not found with id: 5"`

---

## Kahan Use Hoti Hai?

```java
// UserServiceImpl mein:
User user = userRepository.findById(id)
    .orElseThrow(() -> new UserNotFoundException(id));
//                           ↑
//              Agar user nahi mila toh yeh exception throw hogi
```

---

## GlobalExceptionHandler Se Connection

Jab yeh exception throw hoti hai, `GlobalExceptionHandler` ise catch karta hai aur proper HTTP response banata hai:

```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)  // 404
            .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
}
```

**Result:** `404 Not Found` response milta hai with error message.

---

## Custom Exception Kyun?

Generic `Exception` throw karna achi practice nahi — code padhne wale ko samajh nahi aata kya galat hua. Custom exceptions se:
- **Code readable** hota hai
- **Specific error handling** possible hota hai
- **Different HTTP status codes** dene mein aasaani hoti hai
