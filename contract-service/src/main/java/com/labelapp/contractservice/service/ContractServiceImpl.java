package com.labelapp.contractservice.service;

import com.labelapp.contractservice.dto.ContractDTO;
import com.labelapp.contractservice.exceptions.ExternalServiceException;
import com.labelapp.contractservice.exceptions.ResourceNotFoundException;
import com.labelapp.contractservice.feign.ArtistClient;
import com.labelapp.contractservice.repository.ContractRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private static final Logger logger =  LoggerFactory.getLogger(ContractServiceImpl.class);


    ContractRepository contractRepository;
    private final ArtistClient artistClient;
    private final ModelMapper modelMapper;


    public ContractServiceImpl(ArtistClient artistClient, ContractRepository contractRepository, ModelMapper modelMapper){
        this.artistClient = artistClient;
        this.contractRepository = contractRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContractDTO> findContractsById(Long artistId) {
        ContractDTO artist;
        try {
            artist = artistClient.getArtistById(artistId);
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new ResourceNotFoundException("Artist with id " + artistId + " not found.");
            } else {
                throw new ExternalServiceException("Error connecting to artist-service: " + e.status());
            }
        }

        return contractRepository.findByArtistId(artistId)
                .stream()
                .map(contract -> modelMapper.map(contract, ContractDTO.class))
                .toList();
    }

}
