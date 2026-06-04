# Music Library — Microservices Capstone

A Music Library web application built with **Java 21** and **Spring Boot** microservices. Users can browse songs and create playlists. Admins can manage songs and users.

## Tech Stack
Java 21 · Spring Boot 3.3 · Spring Cloud · Spring Security + JWT · Eureka · OpenFeign · H2 Database · HTML/CSS/JS

## How to Run

```bash
mvn clean install -DskipTests
```
Start each service in order: `eureka-server` → `config-server` → `user-service` → `admin-service` → `song-service` → `playlist-service` → `notification-service` → `api-gateway` → `frontend-service`

```bash
cd <service-name> && mvn spring-boot:run
```

Open → **http://localhost:8086**

## Default Admin
`admin@musiclibrary.com` / `admin123`
