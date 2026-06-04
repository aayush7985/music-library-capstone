# Music Library — Microservices Capstone

A Music Library web application built with **Java 21** and **Spring Boot** microservices architecture. Users can browse songs, create playlists, and get notifications. Admins can manage songs and users.

---

## Tech Stack

- Java 21, Spring Boot 3.3.5, Spring Cloud
- Spring Security + JWT Authentication
- Netflix Eureka, Spring Cloud Gateway, OpenFeign
- H2 In-memory Database, SpringDoc Swagger
- HTML, CSS, JavaScript (Spotify-style UI)

---

## Microservices

| Service | Port |
|---------|------|
| Eureka Server | 8761 |
| Config Server | 8888 |
| API Gateway | 9090 |
| User Service | 8081 |
| Admin Service | 8082 |
| Song Service | 8083 |
| Playlist Service | 8084 |
| Notification Service | 8085 |
| Frontend Service | 8086 |

---

## How to Run

```bash
# Step 1 - Build
mvn clean install -DskipTests

# Step 2 - Start each service in a separate terminal (in this order)
cd eureka-server        && mvn spring-boot:run
cd config-server        && mvn spring-boot:run
cd user-service         && mvn spring-boot:run
cd admin-service        && mvn spring-boot:run
cd song-service         && mvn spring-boot:run
cd playlist-service     && mvn spring-boot:run
cd notification-service && mvn spring-boot:run
cd api-gateway          && mvn spring-boot:run
cd frontend-service     && mvn spring-boot:run
```

Open → **http://localhost:8086**

---

## Default Admin
- Email: `admin@musiclibrary.com`
- Password: `admin123`

---

**Developer:** Aayush Pandey | Great Learning Capstone
