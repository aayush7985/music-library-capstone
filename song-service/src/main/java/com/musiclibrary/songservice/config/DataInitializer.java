package com.musiclibrary.songservice.config;

import com.musiclibrary.songservice.entity.Song;
import com.musiclibrary.songservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SongRepository songRepository;

    @Override
    public void run(String... args) {
        if (songRepository.count() == 0) {
            songRepository.save(Song.builder().title("Blinding Lights").singer("The Weeknd")
                    .musicDirector("Max Martin").albumName("After Hours").genre("Pop")
                    .releaseDate(LocalDate.of(2019, 11, 29)).duration("3:20").visible(true).build());
            songRepository.save(Song.builder().title("Shape of You").singer("Ed Sheeran")
                    .musicDirector("Ed Sheeran").albumName("÷").genre("Pop")
                    .releaseDate(LocalDate.of(2017, 1, 6)).duration("3:53").visible(true).build());
            songRepository.save(Song.builder().title("Bohemian Rhapsody").singer("Queen")
                    .musicDirector("Freddie Mercury").albumName("A Night at the Opera").genre("Rock")
                    .releaseDate(LocalDate.of(1975, 10, 31)).duration("5:54").visible(true).build());
            songRepository.save(Song.builder().title("Tum Hi Ho").singer("Arijit Singh")
                    .musicDirector("Mithoon").albumName("Aashiqui 2").genre("Bollywood")
                    .releaseDate(LocalDate.of(2013, 4, 26)).duration("4:22").visible(true).build());
            songRepository.save(Song.builder().title("Levitating").singer("Dua Lipa")
                    .musicDirector("Koz").albumName("Future Nostalgia").genre("Pop")
                    .releaseDate(LocalDate.of(2020, 10, 1)).duration("3:23").visible(true).build());
            System.out.println("Sample songs loaded into database.");
        }
    }
}
