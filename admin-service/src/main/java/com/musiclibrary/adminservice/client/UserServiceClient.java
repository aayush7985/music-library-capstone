package com.musiclibrary.adminservice.client;

import com.musiclibrary.adminservice.dto.UpdateUserDTO;
import com.musiclibrary.adminservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserServiceClient {

    @GetMapping
    List<UserResponseDTO> getAllUsers();

    @GetMapping("/{id}")
    UserResponseDTO getUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO dto);

    @DeleteMapping("/{id}")
    Map<String, String> deleteUser(@PathVariable Long id);

    @PatchMapping("/{id}/disable")
    Map<String, String> disableUser(@PathVariable Long id);

    @PatchMapping("/{id}/enable")
    Map<String, String> enableUser(@PathVariable Long id);
}
