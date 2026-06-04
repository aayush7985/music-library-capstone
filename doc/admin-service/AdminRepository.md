# AdminRepository.java

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/repository/AdminRepository.java`

## Kya Karta Hai?

Admin database operations ke liye JPA repository.

```java
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    boolean existsByEmail(String email);
}
```

| Method | SQL Generated | Use |
|---|---|---|
| `findByEmail(email)` | `WHERE email = ?` | Login verification |
| `existsByEmail(email)` | `COUNT WHERE email = ?` | DataInitializer mein duplicate check |

Free methods: `save()`, `findById()`, `findAll()`, `deleteById()` sab JpaRepository se milte hain.
