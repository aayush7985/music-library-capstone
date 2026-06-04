# UpdateUserDTO.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/dto/UpdateUserDTO.java`

## Kya Karta Hai?

Admin jab kisi user ka data update karta hai tab yeh DTO Feign se User Service ko bheja jaata hai.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateUserDTO {
    private String name;
    private String phoneNumber;
    private String password;
}
```

User Service mein bhi same UpdateUserDTO hai — Feign request body mein yeh JSON jata hai aur User Service ise apne UpdateUserDTO mein convert karta hai. Matching fields zaroori hain.
