package com.labelapp.producerservice.service;


import com.labelapp.producerservice.dto.ProducerDTO;

import java.util.List;

public interface ProducerService {

    List<ProducerDTO> findAllProducers();
    ProducerDTO findProducerById(Long id);
    void createProducer(ProducerDTO producerDTO);
    void updateProducer(Long id, ProducerDTO producerDTO);
    void deleteProducerById(Long id);
}
