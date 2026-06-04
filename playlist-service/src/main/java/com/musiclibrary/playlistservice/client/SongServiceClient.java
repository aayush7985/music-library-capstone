package com.musiclibrary.playlistservice.client;

import com.musiclibrary.playlistservice.dto.SongResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "song-service", path = "/api/songs")
public interface SongServiceClient {

    @GetMapping("/{id}")
    SongResponseDTO getSongById(@PathVariable Long id);
}
