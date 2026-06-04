package com.musiclibrary.songservice.repository;

import com.musiclibrary.songservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByVisible(boolean visible);

    @Query("SELECT s FROM Song s WHERE s.visible = true AND " +
           "(LOWER(s.singer) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.albumName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.musicDirector) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(s.title) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Song> searchSongs(@Param("query") String query);

    @Query("SELECT s FROM Song s WHERE s.visible = true AND LOWER(s.singer) LIKE LOWER(CONCAT('%', :singer, '%'))")
    List<Song> findBySingerContainingIgnoreCaseAndVisible(@Param("singer") String singer);

    @Query("SELECT s FROM Song s WHERE s.visible = true AND LOWER(s.albumName) LIKE LOWER(CONCAT('%', :album, '%'))")
    List<Song> findByAlbumNameContainingIgnoreCaseAndVisible(@Param("album") String album);

    @Query("SELECT s FROM Song s WHERE s.visible = true AND LOWER(s.musicDirector) LIKE LOWER(CONCAT('%', :director, '%'))")
    List<Song> findByMusicDirectorContainingIgnoreCaseAndVisible(@Param("director") String director);
}
