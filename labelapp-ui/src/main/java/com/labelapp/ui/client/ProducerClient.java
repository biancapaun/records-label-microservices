package com.labelapp.ui.client;

import com.labelapp.ui.dto.ProducerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "producer-service")
public interface ProducerClient {

    @GetMapping("/producers/sorted/asc")
    List<ProducerDTO> getAllProducersSortedAsc();

    @GetMapping("/producers/sorted/desc")
    List<ProducerDTO> getAllProducersSortedDesc();

    @GetMapping("/producers")
    List<ProducerDTO> getAllProducers(@RequestParam(name = "sortDir", defaultValue = "asc") String sortDir);


    @PostMapping("/producers")
    void createProducer(@RequestBody ProducerDTO producerDTO);

    @GetMapping("/producers/{id}")
    ProducerDTO getProducerById(@PathVariable Long id);

    @PutMapping("/producers/{id}")
    void updateProducer(@PathVariable Long id, @RequestBody  ProducerDTO producerDTO);

    @DeleteMapping("/producers/{id}")
    void deleteProducer(@PathVariable Long id);

    @GetMapping("/producers/count")
    Long countProducers();
}