# UserServiceImpl.java

## File Location
`user-service/src/main/java/com/musiclibrary/userservice/service/impl/UserServiceImpl.java`

## Kya Karta Hai Yeh File?

Yeh `UserService` interface ki **actual implementation** hai. Saara business logic yahan hota hai — validation, password encryption, JWT generation, database operations sab kuch.

---

## Code Explanation (Hinglish)

### Class Setup
```java
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
```

- `@Service` — Spring ko batata hai ki yeh business logic layer hai.
- `@RequiredArgsConstructor` — Lombok: `final` fields ka constructor automatically banega (constructor injection).
- **Constructor injection** prefer karte hain `@Autowired` se — testing easier hoti hai.

---

### register() Method
```java
public UserResponseDTO register(UserRegistrationDTO dto) {
    // 1. Email duplicate check
    if (userRepository.existsByEmail(dto.getEmail())) {
        throw new EmailAlreadyExistsException(dto.getEmail());
    }

    // 2. Entity build karo
    User user = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .phoneNumber(dto.getPhoneNumber())
            .password(passwordEncoder.encode(dto.getPassword()))  // BCrypt encrypt!
            .role(User.Role.ROLE_USER)
            .enabled(true)
            .build();

    // 3. Save karo
    User saved = userRepository.save(user);

    // 4. Response DTO return karo (password nahi)
    return toResponseDTO(saved);
}
```

**Important:** `passwordEncoder.encode(dto.getPassword())` — Password **plain text mein KABHI save nahi hota**. BCrypt se hash banta hai jo irreversible hai.

---

### login() Method
```java
public JwtResponseDTO login(UserLoginDTO dto) {
    // 1. Email se user dhundho
    User user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(InvalidCredentialsException::new);

    // 2. Password match karo
    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
        throw new InvalidCredentialsException();
    }

    // 3. Account active hai?
    if (!user.isEnabled()) {
        throw new RuntimeException("Account is disabled. Contact admin.");
    }

    // 4. JWT generate karo
    String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());

    return JwtResponseDTO.builder()
            .token(token)
            .userId(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .role(user.getRole().name())
            .build();
}
```

`passwordEncoder.matches(plain, hashed)` — BCrypt plain password ko hash se compare karta hai. Direct compare nahi hota kyunki same password ka har baar alag hash ban sakta hai.

---

### updateUser() Method
```java
public UserResponseDTO updateUser(Long id, UpdateUserDTO dto) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

    if (dto.getName() != null && !dto.getName().isBlank())
        user.setName(dto.getName());
    if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank())
        user.setPhoneNumber(dto.getPhoneNumber());
    if (dto.getPassword() != null && !dto.getPassword().isBlank())
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

    return toResponseDTO(userRepository.save(user));
}
```

**Partial update** — sirf jo field bheja woh update hota hai. Null values ignore karta hai.

---

### toResponseDTO() Helper
```java
private UserResponseDTO toResponseDTO(User user) {
    return UserResponseDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .role(user.getRole().name())
            .enabled(user.isEnabled())
            .createdAt(user.getCreatedAt())
            .build();
    // password intentionally nahi add kiya!
}
```

Yeh **private helper method** hai — Entity ko DTO mein convert karta hai. Password field deliberately chhoda gaya hai.
