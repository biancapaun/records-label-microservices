package com.labelapp.production_service.feign;

import com.labelapp.production_service.dto.ProducerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producer-service")
public interface ProducerClient {

    @GetMapping("/producers/{id}")
    ProducerDTO getProducerById(@PathVariable("id") Long producerId);
}
