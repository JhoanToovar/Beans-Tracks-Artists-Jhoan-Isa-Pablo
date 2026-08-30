package com.repository;

import com.model.Track;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TrackRepositoryImpl implements ITrackRepository {

    private HashMap<Integer, Track> tracks;

    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public TrackRepositoryImpl() {
        tracks = new HashMap<>();
    }

    @Override
    public Collection<Track> getTracks() {
        return tracks.values();
    }

    @Override
    public Track createTrack(Track track) {
        if (track.getId() == 0) {
            track.setId(idGenerator.incrementAndGet());
        }
        tracks.put(track.getId(), track);
        return track;
    }

    @Override
    public Track deleteTrack(int trackId) {
        return tracks.remove(trackId);
    }

    @PostConstruct
    private void initialize() {
        // Eladio Carrión
        seedTrack("Sauce", "Trap Latino", "3:15", "Sauce Boyz");
        seedTrack("Kemba Walker", "Trap Latino", "3:32", "Sauce Boyz");
        seedTrack("Fantasma", "Trap Latino", "3:41", "3MEN2 KBRN");
        seedTrack("Fendi", "Trap Latino", "2:58", "3MEN2 KBRN");
        seedTrack("La Vecindad Remix", "Trap Latino", "4:02", "Single");

        // Caifanes
        seedTrack("La Célula que Explota", "Rock en Español", "5:02", "El Silencio");
        seedTrack("La Negra Tomasa", "Rock en Español", "4:34", "El Diablito");
        seedTrack("Afuera", "Rock en Español", "4:20", "El Diablito");
        seedTrack("Nubes", "Rock en Español", "5:10", "El Nervio del Volcán");
        seedTrack("Antes de que nos Olviden", "Rock en Español", "4:45", "El Nervio del Volcán");

        // Shakira
        seedTrack("Hips Don't Lie", "Pop Latino", "3:39", "Oral Fixation Vol. 2");
        seedTrack("Whenever, Wherever", "Pop Latino", "3:20", "Laundry Service");
        seedTrack("Waka Waka (This Time for Africa)", "Pop Latino", "3:32", "Sale el Sol");
        seedTrack("Estoy Aquí", "Pop Latino", "3:15", "Pies Descalzos");
        seedTrack("Ojos Así", "Pop Latino", "4:36", "MTV Unplugged");

        // Milo j
        seedTrack("222", "RnB Urbano", "2:42", "16");
        seedTrack("Uno de Cada 10", "RnB Urbano", "2:55", "16");
        seedTrack("Vuelta y Vuelta", "RnB Urbano", "3:05", "Los Pasos del Círculo");
        seedTrack("Sincero", "RnB Urbano", "2:38", "Los Pasos del Círculo");
        seedTrack("Amigos", "RnB Urbano", "3:12", "16");

        // Blessd
        seedTrack("Do Me Bien", "Reggaeton", "3:24", "Bendecido");
        seedTrack("Medallo City", "Reggaeton", "3:40", "Bendecido");
        seedTrack("Ando", "Reggaeton", "3:10", "Single");
        seedTrack("La Morena", "Reggaeton", "3:18", "Bendecido 2");
        seedTrack("Ojalá", "Reggaeton", "3:05", "Single");

        // Andrés Cepeda
        seedTrack("Lo Que en Ti Veo", "Balada Pop", "4:05", "Con Quién Se Queda el Perro");
        seedTrack("Voy a Olvidarme de Mí", "Balada Pop", "3:58", "Día Tras Día");
        seedTrack("Da Una Señal", "Balada Pop", "3:47", "Con Quién Se Queda el Perro");
        seedTrack("Sé Que Te Vas", "Balada Pop", "4:12", "Con Quién Se Queda el Perro");
        seedTrack("No Vuelvo a Amar", "Balada Pop", "3:50", "Buscándote");

        // Paulo Londra
        seedTrack("Adán y Eva", "Trap", "3:19", "Homerun");
        seedTrack("Nena Maldición", "Trap", "3:22", "Homerun");
        seedTrack("Plan A", "Trap", "3:05", "Single");
        seedTrack("Chance", "Trap", "3:11", "Homerun");
        seedTrack("Party", "Trap", "3:28", "Single");

        // Bad Bunny
        seedTrack("Tití Me Preguntó", "Reggaeton", "4:03", "Un Verano Sin Ti");
        seedTrack("Callaita", "Trap Latino", "3:59", "Single");
        seedTrack("Yo Perreo Sola", "Reggaeton", "3:15", "YHLQMDLG");
        seedTrack("Monaco", "Reggaeton", "3:03", "Nadie Sabe Lo Que Va a Pasar Mañana");
        seedTrack("Efecto", "Reggaeton", "3:48", "YHLQMDLG");

        // LitKillah
        seedTrack("Rulai", "Trap", "2:50", "Single");
        seedTrack("Bandido", "Trap", "3:02", "Single");
        seedTrack("Ohnana", "Pop Urbano", "2:47", "Single");
        seedTrack("Un Vistazo", "Trap", "3:00", "Single");
        seedTrack("Ella No Es Tuya", "Trap", "3:14", "Single");

        // Michael Jackson
        seedTrack("Billie Jean", "Pop", "4:54", "Thriller");
        seedTrack("Beat It", "Pop Rock", "4:18", "Thriller");
        seedTrack("Thriller", "Pop", "5:57", "Thriller");
        seedTrack("Smooth Criminal", "Pop", "4:17", "Bad");
        seedTrack("Man in the Mirror", "Pop", "5:19", "Bad");
    }

    private void seedTrack(String title, String genre, String duration, String albumTitle) {
        Track track = new Track(idGenerator.incrementAndGet(), title, genre, duration, albumTitle);
        tracks.put(track.getId(), track);
    }
}
