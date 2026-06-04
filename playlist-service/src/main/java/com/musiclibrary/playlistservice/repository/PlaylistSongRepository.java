package com.musiclibrary.playlistservice.repository;

import com.musiclibrary.playlistservice.entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    List<PlaylistSong> findByPlaylistId(Long playlistId);
    Optional<PlaylistSong> findByPlaylistIdAndSongId(Long playlistId, Long songId);
    boolean existsByPlaylistIdAndSongId(Long playlistId, Long songId);

    @Query("SELECT ps FROM PlaylistSong ps WHERE ps.playlist.id = :playlistId " +
           "AND LOWER(ps.songTitle) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<PlaylistSong> searchByTitle(@Param("playlistId") Long playlistId, @Param("title") String title);
}
