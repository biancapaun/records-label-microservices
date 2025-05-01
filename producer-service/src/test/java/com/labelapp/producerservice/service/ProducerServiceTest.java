package com.labelapp.producerservice.service;

import com.labelapp.producerservice.domain.Producer;
import com.labelapp.producerservice.dto.ProducerDTO;
import com.labelapp.producerservice.repository.ProducerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProducerServiceTest {

    @Mock
    ProducerRepository producerRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ProducerServiceImpl producerService;

    @Test
    void testFindAllProducers(){
        // given
        Producer Producer = new Producer();
        List<Producer> producers = List.of(Producer);

        when(producerRepository.findAllByOrderByIdAsc()).thenReturn(producers);
        when(modelMapper.map(any(Producer.class), eq(ProducerDTO.class))).thenReturn(new ProducerDTO());

        // when
        List<ProducerDTO> result = producerService.findAllProducers();

        // then
        assertEquals(1, result.size());
        verify(producerRepository, times(1)).findAllByOrderByIdAsc();

    }

    @Test
    void testFindProducerById_whenProducerExists_expectProducerDto() {
        // given
        Long id = 1L;
        Producer producer = new Producer();
        producer.setId(id);
        producer.setName("John Smith");

        ProducerDTO dto = new ProducerDTO();
        dto.setName("John Smith");

        when(producerRepository.findById(id)).thenReturn(Optional.of(producer));
        when(modelMapper.map(producer, ProducerDTO.class)).thenReturn(dto);

        // when
        ProducerDTO result = producerService.findProducerById(id);

        // then
        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        verify(producerRepository, times(1)).findById(id);
    }

    @Test
    void testCreateProducer_shouldMapAndSaveEntity() {
        // given
        ProducerDTO dto = new ProducerDTO();
        dto.setName("John Smith");

        Producer producer = new Producer();
        producer.setName("John Smith");

        when(modelMapper.map(dto, Producer.class)).thenReturn(producer);

        // when
        producerService.createProducer(dto);

        // then
        verify(modelMapper).map(dto, Producer.class);
        verify(producerRepository).save(producer);
    }

    @Test
    void testUpdateProducer_whenProducerExists_shouldUpdateFieldsAndSave() {
        // given
        Long id = 1L;
        ProducerDTO dto = new ProducerDTO();
        dto.setName("New Name");
        dto.setOriginCountry("New Country");
        dto.setSpecialization("New Specialization");

        Producer existingProducer = new Producer();
        existingProducer.setId(id);
        existingProducer.setName("Old Scene Name");
        existingProducer.setOriginCountry("UK");
        existingProducer.setSpecialization("Mix");

        when(producerRepository.findById(id)).thenReturn(Optional.of(existingProducer));

        // when
        producerService.updateProducer(id, dto);

        // then
        assertEquals("New Name", existingProducer.getName());
        assertEquals("New Country", existingProducer.getOriginCountry());
        assertEquals("New Specialization", existingProducer.getSpecialization());

        verify(producerRepository).save(existingProducer);
    }


    @Test
    void testDeleteProducerById_whenProducerExists_shouldCallRepositoryDelete() {
        // given
        Long id = 1L;

        // when
        producerService.deleteProducerById(id);

        // then
        verify(producerRepository).deleteById(id);
    }







}
