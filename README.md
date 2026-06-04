# 🎵 Music Library — Microservices Capstone Project

A full-stack Music Library application built using **Java 21** and **Spring Boot 3.3** with a microservices architecture. Users can browse songs, create playlists, and receive notifications. Admins can manage songs and users.

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 21 | Core Language |
| Spring Boot 3.3.5 | Microservices Framework |
| Spring Cloud 2023.0.3 | Service Discovery & Config |
| Spring Security + JWT | Authentication & Authorization |
| Netflix Eureka | Service Registry |
| Spring Cloud Gateway | API Gateway & Routing |
| Spring Cloud Config | Centralized Configuration |
| OpenFeign | Inter-service Communication |
| H2 Database | In-memory Database (per service) |
| SpringDoc OpenAPI | Swagger API Documentation |
| HTML, CSS, JavaScript | Frontend (Spotify-style UI) |
| Lombok | Boilerplate Reduction |

---

## 🏗️ Microservices Architecture

```
                        ┌─────────────────┐
                        │   Eureka Server  │
                        │     :8761        │
                        └────────┬────────┘
                                 │
                        ┌────────┴────────┐
                        │  Config Server   │
                        │     :8888        │
                        └────────┬────────┘
                                 │
                    ┌────────────┴────────────┐
                    │      API Gateway         │
                    │         :9090            │
                    └────────────┬────────────┘
                                 │
        ┌──────────┬─────────────┼─────────────┬──────────┐
        │          │             │             │          │
   User Svc   Admin Svc    Song Svc    Playlist Svc  Notif Svc
    :8081       :8082        :8083        :8084       :8085
```

---

## 🔑 Features

### User
- Register and Login with JWT authentication
- Browse all available songs
- Search songs by title, singer, album, music director
- Create, update and delete playlists
- Add / remove songs from playlists
- Search songs inside a playlist
- Play, pause, stop, shuffle and repeat controls (UI)
- Receive notifications when new songs are added

### Admin
- Secure admin login
- Add, update, delete songs
- Toggle song visibility (show/hide from users)
- View and manage all registered users
- Enable / disable user accounts
- Notifications sent automatically when a new song is added

---

## 📁 Project Structure

```
music-library-capstone/
├── eureka-server/          # Service Registry (port 8761)
├── config-server/          # Central Config Server (port 8888)
├── api-gateway/            # Gateway + JWT Filter (port 9090)
├── user-service/           # User CRUD + Auth (port 8081)
├── admin-service/          # Admin CRUD + User Mgmt (port 8082)
├── song-service/           # Song CRUD + Search (port 8083)
├── playlist-service/       # Playlist + Songs (port 8084)
├── notification-service/   # In-app Notifications (port 8085)
├── frontend-service/       # Static Frontend UI (port 8086)
├── config-repo/            # Config YML files (local Git)
└── pom.xml                 # Parent POM
```

---

## 🚀 How to Run

### Step 1 — Build
```bash
cd "MusicPlayer Capstone"
mvn clean install -DskipTests
```

### Step 2 — Start Services in Order

Open a separate terminal for each:

```bash
cd eureka-server       && mvn spring-boot:run   # Port 8761
cd config-server       && mvn spring-boot:run   # Port 8888
cd user-service        && mvn spring-boot:run   # Port 8081
cd admin-service       && mvn spring-boot:run   # Port 8082
cd song-service        && mvn spring-boot:run   # Port 8083
cd playlist-service    && mvn spring-boot:run   # Port 8084
cd notification-service && mvn spring-boot:run  # Port 8085
cd api-gateway         && mvn spring-boot:run   # Port 9090
cd frontend-service    && mvn spring-boot:run   # Port 8086
```

### Step 3 — Open in Browser
| Page | URL |
|------|-----|
| Frontend App | http://localhost:8086 |
| Eureka Dashboard | http://localhost:8761 |
| User Swagger | http://localhost:8081/swagger-ui.html |
| Admin Swagger | http://localhost:8082/swagger-ui.html |
| Song Swagger | http://localhost:8083/swagger-ui.html |

---

## 🔐 Default Credentials

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@musiclibrary.com | admin123 |
| User | Register at /user-register.html | — |

---

## 📌 API Endpoints

### User Service (8081)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/users/register | Register new user |
| POST | /api/users/login | User login |
| GET | /api/users/{id} | Get user by ID |
| PUT | /api/users/{id} | Update user |
| DELETE | /api/users/{id} | Delete user |

### Song Service (8083)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/songs | Get all visible songs |
| GET | /api/songs/search?query= | Search songs |
| POST | /api/songs | Add song (Admin) |
| PUT | /api/songs/{id} | Update song (Admin) |
| DELETE | /api/songs/{id} | Delete song (Admin) |
| PATCH | /api/songs/{id}/visibility | Toggle visibility (Admin) |

### Playlist Service (8084)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/playlists | Get user playlists |
| POST | /api/playlists | Create playlist |
| PUT | /api/playlists/{id} | Update playlist |
| DELETE | /api/playlists/{id} | Delete playlist |
| POST | /api/playlists/{id}/songs | Add song to playlist |
| DELETE | /api/playlists/{id}/songs/{songId} | Remove song |

---

## 👨‍💻 Developer

**Aayush Pandey**  
Great Learning — Java Full Stack Capstone Project
