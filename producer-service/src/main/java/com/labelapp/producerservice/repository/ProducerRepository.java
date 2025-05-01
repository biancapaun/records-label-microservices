package com.labelapp.producerservice.repository;

import com.labelapp.producerservice.domain.Producer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    List<Producer> findAllByOrderByIdAsc();
    List<Producer> findAllByOrderByNameAsc();
    List<Producer> findAllByOrderByNameDesc();

}
