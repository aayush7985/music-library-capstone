@echo off
title Music Library - Stopping All Services
color 0C
cls

echo ============================================================
echo         MUSIC LIBRARY - STOPPING ALL SERVICES
echo ============================================================
echo.

REM Kill all Java processes on our ports
echo Stopping all microservices...

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8761 " ^| findstr "LISTENING"') do (
    echo Stopping Eureka Server (Port 8761) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8888 " ^| findstr "LISTENING"') do (
    echo Stopping Config Server (Port 8888) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8081 " ^| findstr "LISTENING"') do (
    echo Stopping User Service (Port 8081) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8082 " ^| findstr "LISTENING"') do (
    echo Stopping Admin Service (Port 8082) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8083 " ^| findstr "LISTENING"') do (
    echo Stopping Song Service (Port 8083) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8084 " ^| findstr "LISTENING"') do (
    echo Stopping Playlist Service (Port 8084) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8085 " ^| findstr "LISTENING"') do (
    echo Stopping Notification Service (Port 8085) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":9090 " ^| findstr "LISTENING"') do (
    echo Stopping API Gateway (Port 9090) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8086 " ^| findstr "LISTENING"') do (
    echo Stopping Frontend Service (Port 8086) - PID %%a
    taskkill /PID %%a /F > nul 2>&1
)

echo.
echo ============================================================
echo   All Music Library services have been stopped!
echo ============================================================
echo.
pause
