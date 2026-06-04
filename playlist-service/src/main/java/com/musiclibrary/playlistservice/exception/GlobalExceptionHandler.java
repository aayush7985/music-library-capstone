package com.musiclibrary.playlistservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(PlaylistNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(SongAlreadyInPlaylistException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(SongAlreadyInPlaylistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Internal server error: " + ex.getMessage(), LocalDateTime.now()));
    }

    public record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
}
