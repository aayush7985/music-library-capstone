package com.musiclibrary.playlistservice.service;

import com.musiclibrary.playlistservice.dto.*;

import java.util.List;

public interface PlaylistService {
    PlaylistResponseDTO createPlaylist(Long userId, PlaylistCreateDTO dto);
    List<PlaylistResponseDTO> getUserPlaylists(Long userId);
    PlaylistResponseDTO getPlaylistById(Long id);
    PlaylistResponseDTO updatePlaylist(Long id, PlaylistCreateDTO dto);
    void deletePlaylist(Long id);
    PlaylistResponseDTO addSongToPlaylist(Long playlistId, AddSongDTO dto);
    void removeSongFromPlaylist(Long playlistId, Long songId);
    List<PlaylistSongDTO> searchSongsInPlaylist(Long playlistId, String title);
}
