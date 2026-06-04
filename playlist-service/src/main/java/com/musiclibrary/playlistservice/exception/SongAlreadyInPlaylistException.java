package com.musiclibrary.playlistservice.exception;

public class SongAlreadyInPlaylistException extends RuntimeException {
    public SongAlreadyInPlaylistException(Long songId) {
        super("Song with id " + songId + " is already in the playlist");
    }
}
