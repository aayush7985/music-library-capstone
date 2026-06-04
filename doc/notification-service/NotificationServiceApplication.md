# NotificationServiceApplication.java

## File Location
`notification-service/src/main/java/com/musiclibrary/notificationservice/NotificationServiceApplication.java`

## Kya Karta Hai?

Notification Service ka entry point. Yeh service **in-app notifications** manage karti hai — jab admin naya song add karta hai toh saare users ko notification milti hai.

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NotificationServiceApplication { ... }
```

`@EnableFeignClients` — User Service se saare users ki list fetch karne ke liye.

## Port: **8085** | Database: `jdbc:h2:mem:notification_db`

## Kaam Kaise Karta Hai?

```
Admin adds song
      ↓
Song Service → notifyNewSong() [Feign to Notification Service]
      ↓
Notification Service → User Service se saare users fetch karo [Feign]
      ↓
Har user ke liye ek Notification row create karo (isRead=false)
      ↓
User dashboard par bell icon mein count badha
      ↓
User click kare → notifications list dikhe
      ↓
User mark as read kare → isRead=true
```
