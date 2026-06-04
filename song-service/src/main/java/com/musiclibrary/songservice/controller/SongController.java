package com.musiclibrary.songservice.controller;

import com.musiclibrary.songservice.dto.SongCreateDTO;
import com.musiclibrary.songservice.dto.SongResponseDTO;
import com.musiclibrary.songservice.service.SongService;
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
@RequestMapping("/api/songs")
@RequiredArgsConstructor
@Tag(name = "Song API", description = "Song management and search")
public class SongController {

    private final SongService songService;

    @PostMapping
    @Operation(summary = "Add a new song (Admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<SongResponseDTO> addSong(@Valid @RequestBody SongCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(dto));
    }

    @GetMapping
    @Operation(summary = "Get all visible songs (User)")
    public ResponseEntity<List<SongResponseDTO>> getAllVisibleSongs() {
        return ResponseEntity.ok(songService.getAllVisibleSongs());
    }

    @GetMapping("/all")
    @Operation(summary = "Get all songs including hidden (Admin)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<SongResponseDTO>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get song details by ID")
    public ResponseEntity<SongResponseDTO> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update song (Admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<SongResponseDTO> updateSong(@PathVariable Long id,
                                                       @Valid @RequestBody SongCreateDTO dto) {
        return ResponseEntity.ok(songService.updateSong(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete song (Admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Map<String, String>> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok(Map.of("message", "Song deleted successfully"));
    }

    @PatchMapping("/{id}/visibility")
    @Operation(summary = "Toggle song visibility (Admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<SongResponseDTO> toggleVisibility(@PathVariable Long id) {
        return ResponseEntity.ok(songService.toggleVisibility(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search songs by title, singer, album, or music director")
    public ResponseEntity<List<SongResponseDTO>> searchSongs(@RequestParam String query) {
        return ResponseEntity.ok(songService.searchSongs(query));
    }
}
