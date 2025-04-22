package com.labelapp.album_service.service;

import com.labelapp.album_service.domain.Album;
import com.labelapp.album_service.dto.AlbumDTO;
import com.labelapp.album_service.dto.ArtistDTO;
import com.labelapp.album_service.exceptions.ExternalServiceException;
import com.labelapp.album_service.exceptions.ResourceNotFoundException;
import com.labelapp.album_service.feign.ArtistClient;
import com.labelapp.album_service.repository.AlbumRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private static final Logger logger = LoggerFactory.getLogger(AlbumServiceImpl.class);

    private final AlbumRepository albumRepository;
    private final ArtistClient artistClient;
    private final ModelMapper modelMapper;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistClient artistClient, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.artistClient = artistClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public AlbumDTO createAlbumForArtist(AlbumDTO albumDTO) {
        logger.info("Creating album '{}' for artist ID: {}", albumDTO.getTitle(), albumDTO.getArtistId());
        Long artistId = albumDTO.getArtistId();
        try {
            ArtistDTO artist = artistClient.getArtistById(artistId);
            if (artist == null) {
                logger.warn("Artist with id {} not found when creating album", albumDTO.getArtistId());
                throw new ResourceNotFoundException("Artist with ID " + artistId + " does not exist!");
            }
        } catch (FeignException e) {
            if (e.status() == 404) {
                logger.warn("Feign Artist with id {} not found when creating album", albumDTO.getArtistId());
                throw new ResourceNotFoundException("Artist with ID " + artistId + " does not exist!");
            } else {
                throw new ExternalServiceException("Error when calling artist-service: " + e.status());
            }
        }

        Album album = modelMapper.map(albumDTO, Album.class);
        album.setArtistId(artistId);

        Album savedEntity = albumRepository.save(album);
        logger.debug("Album '{}' saved successfully for artist ID: {}", album.getTitle(), albumDTO.getArtistId());
        return modelMapper.map(savedEntity, AlbumDTO.class);
    }

    @Override
    public List<AlbumDTO> findAlbumsByArtistId(Long artistId) {
        logger.info("Fetching albums for artist ID: {}", artistId);
        List<Album> albums = albumRepository.findByArtistId(artistId);

        return albums.stream()
                .map(album -> modelMapper.map(album, AlbumDTO.class))
                .toList();
    }

    @Override
    public long findAllAlbums() {
        return albumRepository.count();
    }


}

