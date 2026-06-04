package com.musiclibrary.songservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Singer is required")
    @Column(nullable = false)
    private String singer;

    @NotBlank(message = "Music director is required")
    @Column(nullable = false)
    private String musicDirector;

    @Column
    private String albumName;

    @Column
    private LocalDate releaseDate;

    @Column
    private String genre;

    @Column
    private String duration;

    @Column
    private String audioUrl = "dummy-audio.mp3";

    @Column(nullable = false)
    private boolean visible = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
