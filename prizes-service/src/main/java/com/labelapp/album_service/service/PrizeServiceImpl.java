package com.labelapp.album_service.service;

import com.labelapp.album_service.dto.PrizeDTO;
import com.labelapp.album_service.exceptions.ExternalServiceException;
import com.labelapp.album_service.exceptions.ResourceNotFoundException;
import com.labelapp.album_service.feign.ArtistClient;
import com.labelapp.album_service.repository.PrizeRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    private final PrizeRepository prizeRepository;
    private final ArtistClient artistClient;
    private final ModelMapper modelMapper;

    public PrizeServiceImpl(PrizeRepository prizeRepository, ArtistClient artistClient, ModelMapper modelMapper) {
        this.prizeRepository = prizeRepository;
        this.artistClient = artistClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PrizeDTO> findPrizesByArtistId(Long artistId) {
        PrizeDTO artist;
        try {
            artist = artistClient.getArtistById(artistId);
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new ResourceNotFoundException("Artist with id " + artistId + " not found.");
            } else {
                throw new ExternalServiceException("Error connecting to artist-service: " + e.status());
            }
        }

        return prizeRepository.findByArtistId(artistId)
                .stream()
                .map(prize -> modelMapper.map(prize, PrizeDTO.class))
                .toList();
    }
}
