package com.labelapp.artistservice.service;

import com.labelapp.artistservice.domain.Artist;
import com.labelapp.artistservice.dto.ArtistDTO;
import com.labelapp.artistservice.exceptions.ResourceNotFoundException;
import com.labelapp.artistservice.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);


    ArtistRepository artistRepository;

    ModelMapper modelMapper;

    public ArtistServiceImpl(ArtistRepository artistRepository, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ArtistDTO> findAllArtists() {
        logger.info("Fetching all artists");
        List<ArtistDTO> artists = artistRepository.findAllByOrderByIdAsc()
                .stream().map(artist -> modelMapper.map(artist, ArtistDTO.class))
                .toList();
        logger.debug("Found {} artists", artists.size());
        return artists;
    }

    @Override
    public ArtistDTO findArtistById(Long id) {
        logger.info("Searching for artist with ID: {}", id);
        Optional<Artist> artistOptional = artistRepository.findById(id);
        if (!artistOptional.isPresent()) {
            logger.warn("Artist with id {} not found", id);
            throw new ResourceNotFoundException("Artist with id " + id + " not found");
        }

        logger.debug("Artist found: {}", artistOptional.get().getSceneName());
        return modelMapper.map(artistOptional.get(), ArtistDTO.class);
    }

    @Override
    @Transactional
    public void createArtist(ArtistDTO artistDTO) {
        logger.info("Creating new artist: {}", artistDTO.getSceneName());
        Artist artistEntity = modelMapper.map(artistDTO, Artist.class);
        artistRepository.save(artistEntity);
        logger.debug("Artist saved with ID: {}", artistEntity.getId());
    }

    @Override
    @Transactional
    public void updateArtist(Long id, ArtistDTO artistDTO) {
        logger.info("Updating artist with ID: {}", id);
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Artist with id {} not found for update", id);
                    return new ResourceNotFoundException("Artist with id " + id + " not found");
                });

        artist.setSceneName(artistDTO.getSceneName());
        artist.setRealName(artistDTO.getRealName());
        artist.setDateOfBirth(artistDTO.getDateOfBirth());
        artist.setOriginCountry(artistDTO.getOriginCountry());

        artistRepository.save(artist);
        logger.info("Artist with ID: {} updated successfully", id);
    }


    @Override
    public void deleteArtistById(Long id) {
        logger.info("Deleting artist with ID: {}", id);
        artistRepository.deleteById(id);
        logger.info("Artist with ID: {} deleted", id);
    }

    public List<ArtistDTO> findAllArtistsSortedAsc() {
        return artistRepository.findAllByOrderBySceneNameAsc()
                .stream()
                .map(artist -> modelMapper.map(artist, ArtistDTO.class))
                .collect(Collectors.toList());
    }

    public List<ArtistDTO> findAllArtistsSortedDesc() {
        return artistRepository.findAllByOrderBySceneNameDesc()
                .stream()
                .map(artist -> modelMapper.map(artist, ArtistDTO.class))
                .collect(Collectors.toList());
    }

    public Long countArtists(){
        return artistRepository.count();
    }


}
