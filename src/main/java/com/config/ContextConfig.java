package com.config;

import com.repository.ArtistRepositoryImpl;
import com.repository.IArtistRepository;
import com.repository.ITrackRepository;
import com.repository.TrackRepositoryImpl;
import com.service.ArtistService;
import com.service.DataInitializer;
import com.service.IArtistService;
import com.service.ITrackService;
import com.service.TrackService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    // Repositories
    @Bean(initMethod = "initialize")
    public TrackRepositoryImpl trackRepository() {
        return new TrackRepositoryImpl();
    }

    @Bean(initMethod = "initialize")
    public ArtistRepositoryImpl artistRepository() {
        return new ArtistRepositoryImpl();
    }

    // Services
    @Bean
    public ArtistService artistService(IArtistRepository artistRepository, ITrackRepository trackRepository) {
        return new ArtistService(artistRepository, trackRepository);
    }

    @Bean
    public ITrackService trackService(ITrackRepository trackRepository, IArtistRepository artistRepository) {
        return new TrackService(trackRepository, artistRepository);
    }

    // Data initializer
    @Bean(initMethod = "linkArtistsAndTracks")
    public DataInitializer dataInitializer(IArtistService artistService, ITrackService trackService) {
        return new DataInitializer(artistService, trackService);
    }
}