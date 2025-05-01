package com.labelapp.producerservice.controller;

import com.labelapp.producerservice.dto.ProducerDTO;
import com.labelapp.producerservice.service.ProducerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerServiceImpl producerService;

    public ProducerController(ProducerServiceImpl producerService) {
        this.producerService = producerService;
    }


    @GetMapping
    public ResponseEntity<List<ProducerDTO>> getAllProducers(
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        List<ProducerDTO> producers = sortDir.equalsIgnoreCase("desc")
                ? producerService.findAllProducersSortedDesc()
                : producerService.findAllProducersSortedAsc();
        return ResponseEntity.ok(producers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerDTO> getProducerById(@PathVariable Long id) {
        return ResponseEntity.ok(producerService.findProducerById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createProducer(@Valid @RequestBody ProducerDTO producerDTO) {
        producerService.createProducer(producerDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProducer(@PathVariable Long id, @RequestBody ProducerDTO producerDTO) {
        producerService.updateProducer(id, producerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        producerService.deleteProducerById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public Long countProducers() {
        return producerService.count();
    }
}

