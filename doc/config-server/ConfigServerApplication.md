# ConfigServerApplication.java

## File Location
`config-server/src/main/java/com/musiclibrary/configserver/ConfigServerApplication.java`

## Kya Karta Hai Yeh File?

Yeh file **Config Server** ka main entry point hai. Iska kaam hai saare microservices ke liye ek **centralized configuration** provide karna — matlab ek hi jagah se saari services ka configuration manage karna.

---

## Code Explanation (Hinglish)

```java
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

### Annotations Ka Matlab

| Annotation | Matlab |
|---|---|
| `@SpringBootApplication` | Yeh ek Spring Boot application hai — auto-configuration enabled. |
| `@EnableConfigServer` | Yeh application ko **Config Server** bana deta hai. Ab yeh doosri services ko unka configuration de sakta hai. |
| `@EnableDiscoveryClient` | Yeh Config Server ko Eureka mein **register** karta hai taaki doosri services ise dhundh sakein. |

---

## Config Server Kaam Kaise Karta Hai?

**Ek real-life example socho:**
- Imagine karo ek **manager** hai jo sabke kaam ki details rakhta hai.
- Har employee (microservice) manager ke paas jaata hai aur poochhta hai — "mera database password kya hai? Mera port kya hai?"
- Manager (Config Server) apni diary (config-repo) dekhta hai aur jawab deta hai.

### Flow:
```
user-service starts
       ↓
"Mujhe apna config chahiye"
       ↓
Config Server se request
       ↓
Config Server → config-repo/user-service.yml padha
       ↓
user-service ko port, DB URL, JWT secret sab mil gaya
```

---

## application.yml Mein Kya Hai?

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: file:///C:/Users/aayush/OneDrive/Desktop/MusicPlayer Capstone/config-repo
          default-label: master
```

- `uri` — Yeh batata hai ki configuration files **kahan** hain (local Git repo).
- `default-label: master` — Git branch ka naam jo use hogi.

---

## Port
- **8888** — Saari services `http://localhost:8888` par config fetch karti hain.
- Example: `http://localhost:8888/user-service/default` — User Service ka config milega.
