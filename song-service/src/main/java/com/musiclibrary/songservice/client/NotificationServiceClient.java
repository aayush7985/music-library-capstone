package com.musiclibrary.songservice.client;

import com.musiclibrary.songservice.dto.NotificationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", path = "/api/notifications")
public interface NotificationServiceClient {

    @PostMapping("/new-song")
    void notifyNewSong(@RequestBody NotificationRequestDTO dto);
}
