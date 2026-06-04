package com.musiclibrary.songservice.service;

import com.musiclibrary.songservice.dto.SongCreateDTO;
import com.musiclibrary.songservice.dto.SongResponseDTO;

import java.util.List;

public interface SongService {
    SongResponseDTO addSong(SongCreateDTO dto);
    SongResponseDTO updateSong(Long id, SongCreateDTO dto);
    void deleteSong(Long id);
    SongResponseDTO getSongById(Long id);
    List<SongResponseDTO> getAllVisibleSongs();
    List<SongResponseDTO> getAllSongs();
    List<SongResponseDTO> searchSongs(String query);
    SongResponseDTO toggleVisibility(Long id);
}
