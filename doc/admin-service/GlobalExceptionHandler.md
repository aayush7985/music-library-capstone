# GlobalExceptionHandler.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/exception/GlobalExceptionHandler.java`

## Kya Karta Hai?

Admin Service ki saari exceptions ko centrally handle karta hai.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAdminNotFound(AdminNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Internal server error: " + ex.getMessage(), ...));
    }

    public record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
}
```

User Service ke GlobalExceptionHandler se similar — sirf `AdminNotFoundException` handle karta hai (extra ones nahi kyunki admin service simpler hai). Har microservice ka apna GlobalExceptionHandler hona chahiye — yahi best practice hai.
