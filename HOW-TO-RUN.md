# Music Library — How to Run

## Prerequisites
- Java 21 installed (`java -version` → should say 21)
- Maven 3.9+ (`mvn -v`)
- All services use H2 in-memory DB — no external DB needed

## Startup Order (IMPORTANT — must start in this order)

### Step 1: Build all modules
```
cd "MusicPlayer Capstone"
mvn clean install -DskipTests
```

### Step 2: Start Eureka Server (Port 8761)
```
cd eureka-server
mvn spring-boot:run
```
Wait until you see: `Started EurekaServerApplication`
Verify: http://localhost:8761

### Step 3: Start Config Server (Port 8888)
```
cd config-server
mvn spring-boot:run
```
Wait until registered in Eureka.

### Step 4: Start all microservices (in separate terminals)
```
cd user-service         && mvn spring-boot:run   # Port 8081
cd admin-service        && mvn spring-boot:run   # Port 8082
cd song-service         && mvn spring-boot:run   # Port 8083
cd playlist-service     && mvn spring-boot:run   # Port 8084
cd notification-service && mvn spring-boot:run   # Port 8085
```

### Step 5: Start API Gateway (Port 8080)
```
cd api-gateway
mvn spring-boot:run
```

### Step 6: Start Frontend Service (Port 8086)
```
cd frontend-service
mvn spring-boot:run
```

## Access the Application
| URL | Description |
|-----|-------------|
| http://localhost:8086 | Frontend (Landing page) — PRIMARY |
| http://localhost:8086/user-login.html | User Login |
| http://localhost:8086/user-register.html | User Registration |
| http://localhost:8086/admin-login.html | Admin Login |
| http://localhost:8080 | API Gateway (all API calls route here) |
| http://localhost:8761 | Eureka Dashboard |

## Default Admin Credentials
- Email: `admin@musiclibrary.com`
- Password: `admin123`

## H2 Database Consoles
| Service | URL |
|---------|-----|
| User DB | http://localhost:8081/h2-console (JDBC: `jdbc:h2:mem:user_db`) |
| Admin DB | http://localhost:8082/h2-console |
| Song DB | http://localhost:8083/h2-console |
| Playlist DB | http://localhost:8084/h2-console |
| Notification DB | http://localhost:8085/h2-console |

## Swagger UI
| Service | URL |
|---------|-----|
| User Service | http://localhost:8081/swagger-ui.html |
| Admin Service | http://localhost:8082/swagger-ui.html |
| Song Service | http://localhost:8083/swagger-ui.html |
| Playlist Service | http://localhost:8084/swagger-ui.html |
| Notification Service | http://localhost:8085/swagger-ui.html |

## Sample Data
- 5 songs are pre-loaded in the song service on startup
- 1 default admin is created on admin-service startup
