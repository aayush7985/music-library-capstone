package com.musiclibrary.playlistservice.controller;

import com.musiclibrary.playlistservice.dto.*;
import com.musiclibrary.playlistservice.service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
@Tag(name = "Playlist API", description = "Playlist management for users")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    @Operation(summary = "Create a playlist", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<PlaylistResponseDTO> createPlaylist(
            @RequestHeader("X-Auth-User-Id") Long userId,
            @Valid @RequestBody PlaylistCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.createPlaylist(userId, dto));
    }

    @GetMapping
    @Operation(summary = "Get all playlists for user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<PlaylistResponseDTO>> getUserPlaylists(
            @RequestHeader("X-Auth-User-Id") Long userId) {
        return ResponseEntity.ok(playlistService.getUserPlaylists(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get playlist by ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<PlaylistResponseDTO> getPlaylistById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update playlist", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<PlaylistResponseDTO> updatePlaylist(@PathVariable Long id,
                                                               @Valid @RequestBody PlaylistCreateDTO dto) {
        return ResponseEntity.ok(playlistService.updatePlaylist(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete playlist", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.ok(Map.of("message", "Playlist deleted successfully"));
    }

    @PostMapping("/{id}/songs")
    @Operation(summary = "Add song to playlist", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<PlaylistResponseDTO> addSong(@PathVariable Long id,
                                                        @Valid @RequestBody AddSongDTO dto) {
        return ResponseEntity.ok(playlistService.addSongToPlaylist(id, dto));
    }

    @DeleteMapping("/{id}/songs/{songId}")
    @Operation(summary = "Remove song from playlist", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> removeSong(@PathVariable Long id,
                                                           @PathVariable Long songId) {
        playlistService.removeSongFromPlaylist(id, songId);
        return ResponseEntity.ok(Map.of("message", "Song removed from playlist"));
    }

    @GetMapping("/{id}/songs/search")
    @Operation(summary = "Search songs in playlist by title", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<PlaylistSongDTO>> searchSongs(@PathVariable Long id,
                                                              @RequestParam String title) {
        return ResponseEntity.ok(playlistService.searchSongsInPlaylist(id, title));
    }
}
