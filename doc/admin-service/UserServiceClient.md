# UserServiceClient.java (Admin Service)

## File Location
`admin-service/src/main/java/com/musiclibrary/adminservice/client/UserServiceClient.java`

## Kya Karta Hai Yeh File?

Yeh **OpenFeign Client** hai — ek interface jo Admin Service ko User Service se REST API calls karne ki facility deta hai, bina actual HTTP code likhe.

---

## Code Explanation (Hinglish)

```java
@FeignClient(name = "user-service", path = "/api/users")
public interface UserServiceClient {

    @GetMapping
    List<UserResponseDTO> getAllUsers();

    @GetMapping("/{id}")
    UserResponseDTO getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO dto);

    @DeleteMapping("/{id}")
    Map<String, String> deleteUser(@PathVariable Long id);

    @PatchMapping("/{id}/disable")
    Map<String, String> disableUser(@PathVariable Long id);

    @PatchMapping("/{id}/enable")
    Map<String, String> enableUser(@PathVariable Long id);
}
```

---

## `@FeignClient` Ka Jadoo

```java
@FeignClient(name = "user-service", path = "/api/users")
```

- `name = "user-service"` — **Eureka** mein is naam se registered service dhundho.
- `path = "/api/users"` — Base path.
- Feign automatically Eureka se User Service ka IP:Port uthata hai aur HTTP call karta hai.
- **Hume manually URL likhni nahi padti!**

---

## Feign Kaise Kaam Karta Hai?

```
Admin requests: getAllUsers()
        ↓
Feign: "user-service kaahan hai?" Eureka se pucho
        ↓
Eureka: "8081 par hai"
        ↓
Feign: GET http://localhost:8081/api/users
        ↓
User Service: response return kiya
        ↓
Admin Service: data admin ko de do
```

---

## Bina Feign Ke (Manual HTTP — bohat code hota)

```java
// Yeh karna padta bina Feign ke — ugly aur error prone!
RestTemplate restTemplate = new RestTemplate();
String url = "http://user-service/api/users";
List<UserResponseDTO> users = restTemplate.exchange(
    url, HttpMethod.GET, null,
    new ParameterizedTypeReference<List<UserResponseDTO>>() {}
).getBody();
```

**Feign se:** sirf interface method call karo — `userServiceClient.getAllUsers()` — baaki sab automatically!
