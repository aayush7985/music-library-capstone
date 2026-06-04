# GlobalExceptionHandler.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/exception/GlobalExceptionHandler.java`

## Kya Karta Hai Yeh File?

Yeh ek **centralized error handler** hai. Poori application mein kahi bhi exception throw ho, yeh class use pakad leti hai aur user ko proper JSON response deti hai.

---

## Code Explanation (Hinglish)

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
```

`@RestControllerAdvice` — Yeh annotation bolta hai ki yeh class **saare controllers ki exceptions** handle karegi — globally. Har controller mein alag try-catch likhne ki zaroorat nahi.

---

### Exception Handlers

```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
}
```
- `@ExceptionHandler(...)` — Specify karo ki kaunsi exception handle karni hai.
- `NOT_FOUND` — HTTP 404 return karo.

```java
@ExceptionHandler(EmailAlreadyExistsException.class)
public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)  // 409
            .body(new ErrorResponse(409, ex.getMessage(), LocalDateTime.now()));
}
```

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
        String field = ((FieldError) error).getField();
        errors.put(field, error.getDefaultMessage());
    });
    return ResponseEntity.badRequest().body(errors);  // 400
}
```
- Yeh tab trigger hota hai jab **@Valid validation fail** hoti hai.
- Saare validation errors ek map mein collect karke return karta hai.
- Example response:
  ```json
  { "email": "Invalid email format", "name": "Name is required" }
  ```

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(500, "Internal server error: " + ex.getMessage(), ...));
}
```
- Koi bhi unexpected exception — **500 Internal Server Error**.

---

### ErrorResponse Record

```java
public record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
```
- Java 16+ ka **Record** feature — immutable data class.
- Automatic getters, constructor, equals/hashCode sab generate hote hain.

**Response format:**
```json
{
  "status": 404,
  "message": "User not found with id: 5",
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## Exception to HTTP Status Mapping

| Exception | HTTP Status |
|---|---|
| `UserNotFoundException` | 404 Not Found |
| `EmailAlreadyExistsException` | 409 Conflict |
| `InvalidCredentialsException` | 401 Unauthorized |
| `MethodArgumentNotValidException` | 400 Bad Request |
| Any other `Exception` | 500 Internal Server Error |
