package com.repository;

import com.model.Artist;
import com.model.Track;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ArtistRepositoryImpl implements IArtistRepository {

    private HashMap<Integer, Artist> artists;

    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public ArtistRepositoryImpl() {
        artists = new HashMap<>();
    }
    @Override
    public Collection<Artist> findAll() {
        return artists.values();
    }


    @Override
    public void save(Artist artist) {
        if (artist.getId() == 0) {
            artist.setId(idGenerator.incrementAndGet());
        }
        artists.put(artist.getId(), artist);
    }

    @Override
    public Artist findById(int id) {
        return artists.get(id);
    }

    @Override
    public Artist findByName(String name) {
        for (Artist artist : artists.values()) {
            if (artist.getName().equalsIgnoreCase(name)) {
                return artist;
            }
        }
        return null;
    }

    @Override
    public Artist deleteById(int id) {
        return artists.remove(id);
    }

    @PostConstruct
    private void initialize() {
        seedArtist("Eladio Carrion", "Puerto Rico");
        seedArtist("Caifanes", "México");
        seedArtist("Shakira", "Colombia");
        seedArtist("Milo j", "Argentina");
        seedArtist("Blessd", "Colombia");
        seedArtist("Andres Cepeda", "Colombia");
        seedArtist("Paulo Londra", "Argentina");
        seedArtist("Bad Bunny", "Puerto Rico");
        seedArtist("LitKillah", "Argentina");
        seedArtist("Michael Jackson", "Estados Unidos");
    }

    private void seedArtist(String name, String nationality) {
        Artist artist = new Artist(idGenerator.incrementAndGet(), name, nationality);
        artists.put(artist.getId(), artist);
    }
}
