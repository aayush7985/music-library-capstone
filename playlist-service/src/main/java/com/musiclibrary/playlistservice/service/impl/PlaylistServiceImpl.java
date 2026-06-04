package com.musiclibrary.playlistservice.service.impl;

import com.musiclibrary.playlistservice.client.SongServiceClient;
import com.musiclibrary.playlistservice.dto.*;
import com.musiclibrary.playlistservice.entity.Playlist;
import com.musiclibrary.playlistservice.entity.PlaylistSong;
import com.musiclibrary.playlistservice.exception.PlaylistNotFoundException;
import com.musiclibrary.playlistservice.exception.SongAlreadyInPlaylistException;
import com.musiclibrary.playlistservice.repository.PlaylistRepository;
import com.musiclibrary.playlistservice.repository.PlaylistSongRepository;
import com.musiclibrary.playlistservice.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistSongRepository playlistSongRepository;
    private final SongServiceClient songServiceClient;

    @Override
    public PlaylistResponseDTO createPlaylist(Long userId, PlaylistCreateDTO dto) {
        Playlist playlist = Playlist.builder()
                .userId(userId)
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return toDTO(playlistRepository.save(playlist));
    }

    @Override
    public List<PlaylistResponseDTO> getUserPlaylists(Long userId) {
        return playlistRepository.findByUserId(userId).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PlaylistResponseDTO getPlaylistById(Long id) {
        return toDTO(playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id)));
    }

    @Override
    public PlaylistResponseDTO updatePlaylist(Long id, PlaylistCreateDTO dto) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        if (dto.getName() != null && !dto.getName().isBlank()) {
            playlist.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            playlist.setDescription(dto.getDescription());
        }
        return toDTO(playlistRepository.save(playlist));
    }

    @Override
    public void deletePlaylist(Long id) {
        if (!playlistRepository.existsById(id)) throw new PlaylistNotFoundException(id);
        playlistRepository.deleteById(id);
    }

    @Override
    public PlaylistResponseDTO addSongToPlaylist(Long playlistId, AddSongDTO dto) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException(playlistId));

        if (playlistSongRepository.existsByPlaylistIdAndSongId(playlistId, dto.getSongId())) {
            throw new SongAlreadyInPlaylistException(dto.getSongId());
        }

        SongResponseDTO song = songServiceClient.getSongById(dto.getSongId());

        int nextPosition = playlist.getSongs().size() + 1;
        PlaylistSong ps = PlaylistSong.builder()
                .playlist(playlist)
                .songId(song.getId())
                .songTitle(song.getTitle())
                .singer(song.getSinger())
                .position(nextPosition)
                .build();
        playlistSongRepository.save(ps);

        return toDTO(playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException(playlistId)));
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        PlaylistSong ps = playlistSongRepository.findByPlaylistIdAndSongId(playlistId, songId)
                .orElseThrow(() -> new RuntimeException("Song not found in playlist"));
        playlistSongRepository.delete(ps);
    }

    @Override
    public List<PlaylistSongDTO> searchSongsInPlaylist(Long playlistId, String title) {
        return playlistSongRepository.searchByTitle(playlistId, title).stream()
                .map(this::toSongDTO).collect(Collectors.toList());
    }

    private PlaylistResponseDTO toDTO(Playlist playlist) {
        List<PlaylistSongDTO> songs = playlist.getSongs().stream()
                .map(this::toSongDTO).collect(Collectors.toList());
        return PlaylistResponseDTO.builder()
                .id(playlist.getId())
                .userId(playlist.getUserId())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .songs(songs)
                .songCount(songs.size())
                .createdAt(playlist.getCreatedAt())
                .updatedAt(playlist.getUpdatedAt())
                .build();
    }

    private PlaylistSongDTO toSongDTO(PlaylistSong ps) {
        return PlaylistSongDTO.builder()
                .id(ps.getId())
                .songId(ps.getSongId())
                .songTitle(ps.getSongTitle())
                .singer(ps.getSinger())
                .position(ps.getPosition())
                .addedAt(ps.getAddedAt())
                .build();
    }
}
