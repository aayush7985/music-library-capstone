package com.musiclibrary.notificationservice.client;

import com.musiclibrary.notificationservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserServiceClient {

    @GetMapping
    List<UserResponseDTO> getAllUsers();
}
