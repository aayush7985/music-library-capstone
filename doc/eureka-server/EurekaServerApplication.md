# EurekaServerApplication.java

## File Location
`eureka-server/src/main/java/com/musiclibrary/eurekaserver/EurekaServerApplication.java`

## Kya Karta Hai Yeh File? (What does this file do?)

Yeh file **Eureka Server** ka entry point hai — matlab poora service discovery server yahi se start hota hai.

---

## Code Explanation (Hinglish)

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

### Annotations Ka Matlab

| Annotation | Matlab |
|---|---|
| `@SpringBootApplication` | Yeh bolta hai ki yeh ek Spring Boot application hai. Auto-configuration, component scan sab automatically ho jaata hai. |
| `@EnableEurekaServer` | Yeh Spring Cloud ko bolta hai ki is application ko **Eureka Server** ki tarah chalao — yaani yeh ek Service Registry banega. |

### `main()` Method
- `SpringApplication.run(...)` — Yeh line poori application ko start karti hai.
- Jab yeh run hota hai, **port 8761** par ek Eureka Dashboard available ho jaata hai.

---

## Eureka Server Kya Hota Hai?

Socho ek **telephone directory** ki tarah:
- Har microservice apna naam aur address yahan **register** karti hai.
- Jab ek service dusri service se baat karni ho, toh woh Eureka se poochti hai — "user-service kahan hai?" Eureka batata hai.
- Isse **Service Discovery** kehte hain.

---

## application.yml Mein Kya Hai?

```yaml
eureka:
  client:
    register-with-eureka: false   # Yeh server khud apne aap ko register nahi karta
    fetch-registry: false          # Yeh doosron ki list bhi nahi lata
```

Matlab Eureka Server sirf **registry maintain karta hai**, khud register nahi hota.

---

## Port
- **8761** — Browser mein `http://localhost:8761` kholo toh Eureka Dashboard dikhega jahan saare registered services nazar aate hain.
