# GlobalExceptionHandler.java (Song Service)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/exception/GlobalExceptionHandler.java`

## Kya Karta Hai?

Song Service ki exceptions ko centrally handle karta hai.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(SongNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(...) {
        // Validation errors map return karo
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)...
    }
}
```

| Exception | HTTP Status |
|---|---|
| `SongNotFoundException` | 404 Not Found |
| `MethodArgumentNotValidException` | 400 Bad Request |
| Generic `Exception` | 500 Internal Server Error |
