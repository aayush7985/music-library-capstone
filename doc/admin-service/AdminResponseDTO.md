# AdminResponseDTO.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/dto/AdminResponseDTO.java`

## Kya Karta Hai?

Admin ki profile information return karne ke liye DTO — password chhupake.

```java
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;
}
```

Password field intentionally nahi hai — security ke liye.
