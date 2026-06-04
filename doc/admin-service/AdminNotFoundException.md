# AdminNotFoundException.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/exception/AdminNotFoundException.java`

## Kya Karta Hai?

Jab admin database mein na mile ya credentials galat ho tab throw hone wali custom exception.

```java
public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
```

Login mein use: `throw new AdminNotFoundException("Invalid credentials")`
GlobalExceptionHandler se **404 Not Found** response generate hota hai.
