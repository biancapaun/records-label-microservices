package com.labelapp.prizesservice.service;

import com.labelapp.prizesservice.dto.PrizeDTO;
import com.labelapp.prizesservice.exceptions.ExternalServiceException;
import com.labelapp.prizesservice.exceptions.ResourceNotFoundException;
import com.labelapp.prizesservice.feign.ArtistClient;
import com.labelapp.prizesservice.repository.PrizeRepository;
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

    public Long countPrizes(){
        return prizeRepository.count();
    }
}
