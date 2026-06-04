# UserRepository.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/repository/UserRepository.java`

## Kya Karta Hai Yeh File?

Yeh **Repository interface** hai jo database ke saath directly interact karta hai. Spring Data JPA yeh sab automatically implement karta hai — hume SQL likhne ki zaroorat nahi!

---

## Code Explanation (Hinglish)

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByEnabled(boolean enabled);
}
```

### `extends JpaRepository<User, Long>`

Yeh extend karne se **free mein** yeh sab methods mil jaate hain:
- `save(user)` — Insert ya update
- `findById(id)` — ID se dhundho
- `findAll()` — Saare users
- `deleteById(id)` — Delete karo
- `count()` — Total count
- `existsById(id)` — Exists check

`User` = kaunsi entity hai, `Long` = primary key ka type.

---

### Custom Methods

```java
Optional<User> findByEmail(String email);
```
- Spring JPA **method name** padh ke automatically SQL generate karta hai.
- `findBy` + `Email` → `SELECT * FROM users WHERE email = ?`
- `Optional` return type — agar user na mile toh `empty` milega, `null` nahi (null-safe programming).

```java
boolean existsByEmail(String email);
```
- `SELECT COUNT(*) > 0 FROM users WHERE email = ?` automatically ban jaata hai.
- Registration mein use: email already hai ya nahi check karne ke liye.

```java
List<User> findAllByEnabled(boolean enabled);
```
- `SELECT * FROM users WHERE enabled = ?`
- Active/disabled users ki list lene ke liye.

---

## JPA Query Magic — Naming Convention

Spring Data JPA **method name se query** automatically generate karta hai:

| Method Name | Generated SQL |
|---|---|
| `findByEmail` | `WHERE email = ?` |
| `findAllByEnabled` | `WHERE enabled = ?` |
| `existsByEmail` | `EXISTS (WHERE email = ?)` |
| `findByNameAndEmail` | `WHERE name = ? AND email = ?` |

Yeh "**Derived Query Methods**" kehte hain — bahut powerful feature hai!

---

## `@Repository` Annotation

- Spring ko batata hai ki yeh ek Data Access Layer component hai.
- Exception translation provide karta hai (database exceptions ko Spring exceptions mein convert).
