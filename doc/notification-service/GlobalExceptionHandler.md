# GlobalExceptionHandler.java (Notification Service)

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/exception/GlobalExceptionHandler.java`

## Kya Karta Hai?

Notification Service ki exceptions handle karta hai.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Internal server error: " + ex.getMessage(),
                        LocalDateTime.now()));
    }

    public record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
}
```

Notification Service relatively simple hai — sirf generic Exception handler hai. Zyada specific exceptions nahi hain kyunki notifications create/read karna kaafi straightforward operation hai. Future mein `NotificationNotFoundException` add ki ja sakti hai.
