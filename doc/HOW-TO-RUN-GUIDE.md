# Music Library — Project Chalane Ka Tarika
### (Teacher Presentation Guide)

---

## Project Overview

Yeh ek **Microservices-based Music Library** application hai jisme:
- **9 Spring Boot services** alag alag ports par chalti hain
- **Java 21** use kiya gaya hai
- **H2 in-memory database** har service ke liye
- **JWT Security** via Spring Security
- **HTML/CSS/JS Frontend** — dark-theme, clean UI

---

## Step 0 — Prerequisites (Pehle Yeh Check Karo)

Terminal/Command Prompt kholo aur yeh commands chalao:

```bash
java -version
# Output mein "21" dikhna chahiye — java version "21.x.x"

mvn -version
# Output mein "Apache Maven 3.x.x" dikhna chahiye
```

Agar Java 21 nahi hai — [Download karo](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

---

## Step 1 — Project Build Karo (Sirf Ek Baar)

```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone"
mvn clean install -DskipTests
```

**Expected output (last lines):**
```
[INFO] BUILD SUCCESS
[INFO] Total time: ~2-3 minutes
```

> Yeh step JAR files banata hai. Ek baar karne ke baad dobara zaroorat nahi jab tak code na badla ho.

---

## Step 2 — 9 Alag Terminals Kholo

**Windows mein:** Start Menu → `cmd` ya `Windows Terminal` → 9 windows kholo

Har terminal ek service ke liye hai. **Start ORDER follow karna zaroori hai!**

---

## Step 3 — Services Start Karo (Is Order Mein)

### Terminal 1 — Eureka Server (Service Registry)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\eureka-server"
mvn spring-boot:run
```
**Wait karo jab tak yeh dikhe:**
```
Started EurekaServerApplication in X seconds
```
**Verify:** Browser mein `http://localhost:8761` kholo — Eureka Dashboard dikhega

---

### Terminal 2 — Config Server (Centralized Configuration)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\config-server"
mvn spring-boot:run
```
**Wait karo jab tak yeh dikhe:**
```
Started ConfigServerApplication in X seconds
```
**Verify:** `http://localhost:8888/user-service/default` — JSON config dikhega

---

### Terminal 3 — User Service (Port 8081)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\user-service"
mvn spring-boot:run
```

### Terminal 4 — Admin Service (Port 8082)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\admin-service"
mvn spring-boot:run
```

### Terminal 5 — Song Service (Port 8083)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\song-service"
mvn spring-boot:run
```

### Terminal 6 — Playlist Service (Port 8084)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\playlist-service"
mvn spring-boot:run
```

### Terminal 7 — Notification Service (Port 8085)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\notification-service"
mvn spring-boot:run
```

> Terminals 3-7 ek saath start kar sakte ho — koi specific order nahi inke beech

---

### Terminal 8 — API Gateway (Port 9090)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\api-gateway"
mvn spring-boot:run
```
**Wait karo jab tak yeh dikhe:**
```
Netty started on port 9090
```

---

### Terminal 9 — Frontend Service (Port 8086)
```bash
cd "C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone\frontend-service"
mvn spring-boot:run
```
**Wait karo jab tak yeh dikhe:**
```
Started FrontendServiceApplication in X seconds
```

---

## Step 4 — Application Kholo

Browser mein yeh URL kholo:

```
http://localhost:8086
```

Landing page dikhe ga — **MusicLib** website!

---

## Step 5 — Demo Karo (Teacher Ko Dikhane Ke Liye)

### Demo 1: User Register aur Login

1. `http://localhost:8086/user-register.html` — Naya account banao
2. Form fill karo: Name, Email, 10-digit Phone, Password
3. **Create Account** click karo
4. `http://localhost:8086/user-login.html` — Login karo
5. Dashboard dikhega — 5 songs dikh rahe hain (Blinding Lights, Shape of You etc.)

---

### Demo 2: Admin Features

1. `http://localhost:8086/admin-login.html` kholo
2. Login karo:
   - Email: `admin@musiclibrary.com`
   - Password: `admin123`
3. Admin Dashboard dikhega (stats: songs, users)
4. **Manage Songs** → Song add karo, visibility toggle karo
5. **Manage Users** → Users list, enable/disable

---

### Demo 3: Song Add aur Notification

1. Admin se login karo
2. **Manage Songs** → **+ Add Song** click karo
3. Song details fill karo → Save
4. Ab user account mein login karo
5. Dashboard par **bell icon (🔔)** par click karo
6. "New song added: ..." notification dikhegi!

---

### Demo 4: Playlist Create aur Songs Add Karo

1. User se login karo
2. **My Playlists** → **+ New Playlist** → naam do
3. Playlist kholo → **+ Add Songs** → songs search karo → Add
4. **Player Controls** dikhenge: Play ▶️ / Stop ⏹ / Shuffle 🔀 / Repeat 🔁

---

### Demo 5: Search Feature

1. User dashboard mein search box mein type karo: `Arijit`
2. Sirf Arijit Singh ke songs filter honge
3. Album name se bhi search karo: `After Hours`

---

## Important URLs — Teacher Ko Dikhao

| URL | Kya Dikhega |
|-----|-------------|
| `http://localhost:8086` | **Frontend Application** (Main App) |
| `http://localhost:8761` | **Eureka Dashboard** — saari registered services dikhao |
| `http://localhost:8081/swagger-ui.html` | **User Service API Docs** |
| `http://localhost:8082/swagger-ui.html` | **Admin Service API Docs** |
| `http://localhost:8083/swagger-ui.html` | **Song Service API Docs** |
| `http://localhost:8084/swagger-ui.html` | **Playlist Service API Docs** |
| `http://localhost:8085/swagger-ui.html` | **Notification Service API Docs** |
| `http://localhost:8081/h2-console` | **User Database** (JDBC URL: `jdbc:h2:mem:user_db`) |
| `http://localhost:8083/h2-console` | **Song Database** (JDBC URL: `jdbc:h2:mem:song_db`) |

---

## Ports Summary

| Service | Port | Role |
|---------|------|------|
| Eureka Server | 8761 | Service Registry |
| Config Server | 8888 | Config Management |
| User Service | 8081 | Registration & Login |
| Admin Service | 8082 | Admin & User Management |
| Song Service | 8083 | Songs CRUD & Search |
| Playlist Service | 8084 | Playlist Management |
| Notification Service | 8085 | Bell Notifications |
| **API Gateway** | **9090** | Single Entry Point (JWT) |
| **Frontend** | **8086** | Web UI |

---

## Common Problems aur Solutions

### Problem: "Port already in use"
```
Web server failed to start. Port 8083 was already in use.
```
**Solution:** Pehle se koi service us port par chal rahi hai.
```bash
# Windows mein port 8083 kill karo:
netstat -ano | findstr :8083
# PID number dekho aur:
taskkill /PID <PID_NUMBER> /F
```

---

### Problem: Config Server se connect nahi ho raha
**Solution:** Eureka aur Config Server pehle start karo, baad mein business services.

---

### Problem: Frontend par songs nahi dikh rahe
**Solution:** Song Service (8083) aur API Gateway (9090) dono running hone chahiye.

---

### Problem: Admin login kaam nahi kar raha
**Solution:** Admin Service start hone par `DataInitializer` automatically default admin banata hai. Agar nahi bana — admin service restart karo.

---

## Teacher Ko Batane Ke Liye Key Points

1. **Microservices Architecture** — Har service independently deploy ho sakti hai
2. **Eureka Server** — Service discovery (telephone directory)
3. **Config Server + Git** — Centralized configuration management
4. **API Gateway** — Single entry point, JWT validation
5. **Spring Security + JWT** — Stateless authentication
6. **OpenFeign** — Inter-service REST communication
7. **H2 Database** — Separate in-memory DB for each service
8. **Spring Data JPA** — Database operations bina SQL likhe
9. **DTOs** — Entities directly expose nahi karte (security)
10. **Global Exception Handler** — Centralized error management
11. **Swagger UI** — Automatic API documentation

---

## Architecture Diagram

```
[Browser :8086]          [Browser]
       |                     |
       v                     v
[Frontend Service]    [Swagger UI (each service)]
  Port: 8086
       |
       | API calls (http://localhost:9090/api/...)
       v
  [API Gateway]  ←→  [Eureka Server :8761]
   Port: 9090           (Registry)
       |
       | JWT validated, routes to:
       |
  ┌────┴──────┬──────────┬──────────┬───────────────┐
  v           v          v          v               v
[User]   [Admin]     [Song]    [Playlist]   [Notification]
:8081     :8082       :8083      :8084          :8085
  |         |           |          |               |
[user_db][admin_db] [song_db] [playlist_db] [notification_db]
```

**Config Server (8888)** — Sabko config deta hai

---

*Project by: Aayush | Java 21 | Spring Boot 3.3.5 | Spring Cloud 2023.0.3*
