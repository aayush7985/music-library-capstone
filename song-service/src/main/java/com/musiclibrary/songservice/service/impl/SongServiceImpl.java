package com.musiclibrary.songservice.service.impl;

import com.musiclibrary.songservice.client.NotificationServiceClient;
import com.musiclibrary.songservice.dto.NotificationRequestDTO;
import com.musiclibrary.songservice.dto.SongCreateDTO;
import com.musiclibrary.songservice.dto.SongResponseDTO;
import com.musiclibrary.songservice.entity.Song;
import com.musiclibrary.songservice.exception.SongNotFoundException;
import com.musiclibrary.songservice.repository.SongRepository;
import com.musiclibrary.songservice.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final NotificationServiceClient notificationServiceClient;

    @Override
    public SongResponseDTO addSong(SongCreateDTO dto) {
        Song song = Song.builder()
                .title(dto.getTitle())
                .singer(dto.getSinger())
                .musicDirector(dto.getMusicDirector())
                .albumName(dto.getAlbumName())
                .releaseDate(dto.getReleaseDate())
                .genre(dto.getGenre())
                .duration(dto.getDuration())
                .audioUrl("dummy-audio.mp3")
                .visible(true)
                .build();
        Song saved = songRepository.save(song);

        try {
            notificationServiceClient.notifyNewSong(NotificationRequestDTO.builder()
                    .songId(saved.getId())
                    .songTitle(saved.getTitle())
                    .message("New song added: " + saved.getTitle() + " by " + saved.getSinger())
                    .build());
        } catch (Exception e) {
            log.warn("Failed to send notification for song {}: {}", saved.getId(), e.getMessage());
        }

        return toDTO(saved);
    }

    @Override
    public SongResponseDTO updateSong(Long id, SongCreateDTO dto) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        if (dto.getTitle() != null) song.setTitle(dto.getTitle());
        if (dto.getSinger() != null) song.setSinger(dto.getSinger());
        if (dto.getMusicDirector() != null) song.setMusicDirector(dto.getMusicDirector());
        if (dto.getAlbumName() != null) song.setAlbumName(dto.getAlbumName());
        if (dto.getReleaseDate() != null) song.setReleaseDate(dto.getReleaseDate());
        if (dto.getGenre() != null) song.setGenre(dto.getGenre());
        if (dto.getDuration() != null) song.setDuration(dto.getDuration());
        return toDTO(songRepository.save(song));
    }

    @Override
    public void deleteSong(Long id) {
        if (!songRepository.existsById(id)) throw new SongNotFoundException(id);
        songRepository.deleteById(id);
    }

    @Override
    public SongResponseDTO getSongById(Long id) {
        return toDTO(songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id)));
    }

    @Override
    public List<SongResponseDTO> getAllVisibleSongs() {
        return songRepository.findByVisible(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SongResponseDTO> getAllSongs() {
        return songRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SongResponseDTO> searchSongs(String query) {
        return songRepository.searchSongs(query).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SongResponseDTO toggleVisibility(Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        song.setVisible(!song.isVisible());
        return toDTO(songRepository.save(song));
    }

    private SongResponseDTO toDTO(Song song) {
        return SongResponseDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .singer(song.getSinger())
                .musicDirector(song.getMusicDirector())
                .albumName(song.getAlbumName())
                .releaseDate(song.getReleaseDate())
                .genre(song.getGenre())
                .duration(song.getDuration())
                .audioUrl(song.getAudioUrl())
                .visible(song.isVisible())
                .createdAt(song.getCreatedAt())
                .build();
    }
}
