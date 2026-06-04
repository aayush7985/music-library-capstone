@echo off
title Music Library - Starting All Services
color 0A
cls

echo ============================================================
echo         MUSIC LIBRARY - MICROSERVICES STARTUP
echo ============================================================
echo.
echo Starting services in correct order...
echo Please wait - do NOT close this window until done!
echo.

set BASE=C:\Users\aayush\OneDrive\Desktop\MusicPlayer Capstone

REM ---- STEP 1: EUREKA SERVER ----
echo [1/9] Starting Eureka Server (Port 8761)...
start "EUREKA SERVER :8761" cmd /k "color 0B && title EUREKA SERVER :8761 && cd /d "%BASE%\eureka-server" && mvn spring-boot:run"

echo     Waiting 25 seconds for Eureka to start...
timeout /t 25 /nobreak > nul

REM ---- STEP 2: CONFIG SERVER ----
echo [2/9] Starting Config Server (Port 8888)...
start "CONFIG SERVER :8888" cmd /k "color 0E && title CONFIG SERVER :8888 && cd /d "%BASE%\config-server" && mvn spring-boot:run"

echo     Waiting 20 seconds for Config Server to start...
timeout /t 20 /nobreak > nul

REM ---- STEP 3-7: BUSINESS SERVICES (all at once) ----
echo [3/9] Starting User Service (Port 8081)...
start "USER SERVICE :8081" cmd /k "color 0D && title USER SERVICE :8081 && cd /d "%BASE%\user-service" && mvn spring-boot:run"

echo [4/9] Starting Admin Service (Port 8082)...
start "ADMIN SERVICE :8082" cmd /k "color 0D && title ADMIN SERVICE :8082 && cd /d "%BASE%\admin-service" && mvn spring-boot:run"

echo [5/9] Starting Song Service (Port 8083)...
start "SONG SERVICE :8083" cmd /k "color 0D && title SONG SERVICE :8083 && cd /d "%BASE%\song-service" && mvn spring-boot:run"

echo [6/9] Starting Playlist Service (Port 8084)...
start "PLAYLIST SERVICE :8084" cmd /k "color 0D && title PLAYLIST SERVICE :8084 && cd /d "%BASE%\playlist-service" && mvn spring-boot:run"

echo [7/9] Starting Notification Service (Port 8085)...
start "NOTIFICATION SERVICE :8085" cmd /k "color 0D && title NOTIFICATION SERVICE :8085 && cd /d "%BASE%\notification-service" && mvn spring-boot:run"

echo     Waiting 30 seconds for business services to start...
timeout /t 30 /nobreak > nul

REM ---- STEP 8: API GATEWAY ----
echo [8/9] Starting API Gateway (Port 9090)...
start "API GATEWAY :9090" cmd /k "color 09 && title API GATEWAY :9090 && cd /d "%BASE%\api-gateway" && mvn spring-boot:run"

echo     Waiting 25 seconds for API Gateway to start...
timeout /t 25 /nobreak > nul

REM ---- STEP 9: FRONTEND SERVICE ----
echo [9/9] Starting Frontend Service (Port 8086)...
start "FRONTEND SERVICE :8086" cmd /k "color 0A && title FRONTEND SERVICE :8086 && cd /d "%BASE%\frontend-service" && mvn spring-boot:run"

echo.
echo ============================================================
echo   ALL SERVICES STARTED! Waiting for frontend...
echo ============================================================
echo.
timeout /t 20 /nobreak > nul

REM ---- OPEN BROWSER ----
echo Opening application in browser...
start "" "http://localhost:8086"

echo.
echo ============================================================
echo   PROJECT IS RUNNING!
echo.
echo   Frontend App  : http://localhost:8086
echo   Eureka Board  : http://localhost:8761
echo   API Gateway   : http://localhost:9090
echo.
echo   Admin Login   : admin@musiclibrary.com / admin123
echo.
echo   To STOP project - run STOP-PROJECT.bat
echo ============================================================
echo.
pause
