package com.musiclibrary.playlistservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @Column(nullable = false)
    private Long songId;

    @Column
    private String songTitle;

    @Column
    private String singer;

    @Column
    private Integer position;

    @Column(nullable = false, updatable = false)
    private LocalDateTime addedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
    }
}
