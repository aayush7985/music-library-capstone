# User.java (Entity)

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/entity/User.java`

## Kya Karta Hai Yeh File?

Yeh **JPA Entity** class hai jo database mein `users` table represent karti hai. Jitne bhi fields yahan define hain, utne hi columns database table mein bante hain.

---

## Code Explanation (Hinglish)

### Class Level Annotations
```java
@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
```

| Annotation | Matlab |
|---|---|
| `@Entity` | Yeh class ek database table represent karti hai. |
| `@Table(name = "users")` | Table ka naam `users` hoga. |
| `@Getter / @Setter` | Lombok — automatically getters aur setters generate karta hai, code likhne ki zaroorat nahi. |
| `@NoArgsConstructor` | Empty constructor banana (JPA ke liye zaroori). |
| `@AllArgsConstructor` | Sab fields wala constructor banana. |
| `@Builder` | Builder pattern — `User.builder().name("Aayush").build()` jaisa convenient object banana. |

---

### Fields (Database Columns)

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```
- `@Id` — Primary key hai yeh field.
- `@GeneratedValue(IDENTITY)` — Database khud automatically ID generate karega (auto-increment).

```java
@NotBlank(message = "Name is required")
@Column(nullable = false)
private String name;
```
- `@NotBlank` — Validation: name blank nahi ho sakta.
- `@Column(nullable = false)` — Database mein yeh column NULL nahi ho sakta.

```java
@Email(message = "Invalid email format")
@Column(nullable = false, unique = true)
private String email;
```
- `@Email` — Email format valid hona chahiye.
- `unique = true` — Ek email se sirf ek account ban sakta hai.

```java
@Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
private String phoneNumber;
```
- `@Pattern` — Regular expression: sirf 10 digits wala phone number allowed.

```java
@Enumerated(EnumType.STRING)
private Role role = Role.ROLE_USER;
```
- `@Enumerated(STRING)` — Enum ko String ke roop mein DB mein store karo (number se nahi).
- Default value: `ROLE_USER`.

```java
private boolean enabled = true;
```
- Admin is flag ko `false` karke user ko **block** kar sakta hai.

---

### Lifecycle Method
```java
@PrePersist
protected void onCreate() {
    this.createdAt = LocalDateTime.now();
}
```
- `@PrePersist` — Jab bhi naya user database mein save hota hai, **automatically** current time `createdAt` mein set ho jaati hai.

---

### Role Enum
```java
public enum Role {
    ROLE_USER
}
```
- `ROLE_USER` — Saare registered users ka default role. `ROLE_ADMIN` alag service mein handle hota hai.

---

## Database Table Structure

| Column | Type | Description |
|---|---|---|
| id | BIGINT | Primary Key, auto-increment |
| name | VARCHAR | User ka naam |
| email | VARCHAR | Unique email |
| phone_number | VARCHAR | 10-digit phone |
| password | VARCHAR | BCrypt encrypted |
| role | VARCHAR | ROLE_USER |
| enabled | BOOLEAN | Active/Disabled |
| created_at | TIMESTAMP | Registration time |
