package com.labelapp.producerservice.service;

import com.labelapp.producerservice.domain.Producer;
import com.labelapp.producerservice.dto.ProducerDTO;
import com.labelapp.producerservice.exceptions.ResourceNotFoundException;
import com.labelapp.producerservice.repository.ProducerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger logger =  LoggerFactory.getLogger(ProducerServiceImpl.class);


    ModelMapper modelMapper;
    ProducerRepository producerRepository;

    public ProducerServiceImpl(ModelMapper modelMapper, ProducerRepository producerRepository) {
        this.modelMapper = modelMapper;
        this.producerRepository = producerRepository;
    }

    @Override
    public List<ProducerDTO> findAllProducers() {
        logger.info("Fetching all producers");
        List<ProducerDTO> producers =  producerRepository.findAllByOrderByIdAsc()
                .stream()
                .map(producer -> modelMapper.map(producer, ProducerDTO.class))
                .toList();
        logger.debug("Found {} producers", producers.size());
        return producers;
    }

    @Override
    public ProducerDTO findProducerById(Long id) {
        logger.info("Searching for producer with ID: {}", id);
        Optional<Producer> producer = producerRepository.findById(id);
        if (!producer.isPresent()) {
            logger.warn("Producer with id {} not found", id);
            throw new ResourceNotFoundException("Producer with id " + id + " not found");
        }
        logger.debug("producer found: {}", producer.get().getName());
        return modelMapper.map(producer.get(), ProducerDTO.class);
    }

    @Override
    @Transactional
    public void createProducer(ProducerDTO producerDTO) {
        logger.info("Creating new producer: {}", producerDTO.getName());
        Producer producer = modelMapper.map(producerDTO, Producer.class);
        producerRepository.save(producer);
        logger.debug("Producer saved with ID: {}", producer.getId());

    }

    @Override
    @Transactional
    public void updateProducer(Long id, ProducerDTO producerDTO) {
        logger.info("Updating producer with ID: {}", id);
        Optional<Producer> producer = producerRepository.findById(id);
        if (!producer.isPresent()) {
            logger.warn("Producer with id {} not found for update", id);
            throw new ResourceNotFoundException("Producer with id " + id + " not found");
        }
        modelMapper.map(producerDTO, producer.get());
        producerRepository.save(producer.get());
        logger.info("Producer with ID: {} updated successfully", id);
    }

    @Override
    @Transactional
    public void deleteProducerById(Long id) {
        logger.info("Deleting producer with ID: {}", id);
        producerRepository.deleteById(id);
        logger.info("Producer with ID: {} deleted", id);
    }

    public List<ProducerDTO> findAllProducersSortedAsc() {
        return producerRepository.findAllByOrderByNameAsc()
                .stream()
                .map(producer -> modelMapper.map(producer, ProducerDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProducerDTO> findAllProducersSortedDesc() {
        return producerRepository.findAllByOrderByNameDesc()
                .stream()
                .map(producer -> modelMapper.map(producer, ProducerDTO.class))
                .collect(Collectors.toList());
    }

    public Long count(){
        return producerRepository.count();
    }


}
