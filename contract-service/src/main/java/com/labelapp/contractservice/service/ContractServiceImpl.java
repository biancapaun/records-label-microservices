package com.recordslabel.labelapp.services;

import com.recordslabel.labelapp.dtos.ContractDTO;
import com.recordslabel.labelapp.entities.Artist;
import com.recordslabel.labelapp.exceptions.ResourceNotFoundException;
import com.recordslabel.labelapp.repositories.ArtistRepository;
import com.recordslabel.labelapp.repositories.ContractRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private static final Logger logger =  LoggerFactory.getLogger(ContractServiceImpl.class);


    ContractRepository contractRepository;
    ArtistRepository artistRepository;

    ModelMapper modelMapper;

    public ContractServiceImpl(ArtistRepository artistRepository, ContractRepository contractRepository, ModelMapper modelMapper){
        this.artistRepository = artistRepository;
        this.contractRepository = contractRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContractDTO> findContractsById(Long id) {
        logger.info("Finding contracts for artist ID: {}",id);
        Optional<Artist> artist = artistRepository.findById(id);
        if (!artist.isPresent()) {
            logger.warn("Artist with id {} not found", id);
            throw new ResourceNotFoundException("Artist with id " + id + " not found");
        }
        List<ContractDTO> contracts =  artist.get().getContracts().stream()
                .map(contract -> modelMapper.map(contract, ContractDTO.class))
                .toList();

        logger.debug("Found {} contracts for artist ID {}", contracts.size(), id);
        return  contracts;
    }
}
