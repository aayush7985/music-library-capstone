# DataInitializer.java (Song Service)

## File Location
`song-service/src/main/java/com/musiclibrary/songservice/config/DataInitializer.java`

## Kya Karta Hai?

Application start hone par **5 sample songs** database mein load karta hai taaki demo ke time empty library na dikhe.

---

## Sample Songs Jo Load Hote Hain

```java
songRepository.save(Song.builder()
    .title("Blinding Lights").singer("The Weeknd")
    .musicDirector("Max Martin").albumName("After Hours")
    .genre("Pop").releaseDate(LocalDate.of(2019, 11, 29))
    .duration("3:20").visible(true).build());

songRepository.save(Song.builder()
    .title("Shape of You").singer("Ed Sheeran")... );

songRepository.save(Song.builder()
    .title("Bohemian Rhapsody").singer("Queen")... );

songRepository.save(Song.builder()
    .title("Tum Hi Ho").singer("Arijit Singh")... );

songRepository.save(Song.builder()
    .title("Levitating").singer("Dua Lipa")... );
```

---

## `CommandLineRunner` Pattern

```java
@Override
public void run(String... args) {
    if (songRepository.count() == 0) {  // Sirf tab load karo jab DB empty ho
        // songs save karo
    }
}
```

`songRepository.count() == 0` check karta hai — agar already songs hain (restart ke baad bhi) toh dobara add mat karo. H2 in-memory hai isliye har restart par count 0 hoga.
