package com.labelapp.contractservice.feign;

import com.labelapp.contractservice.dto.ContractDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "artist-service")
public interface ArtistClient {

    @GetMapping("/artists/{id}")
    ContractDTO getArtistById(@PathVariable("id") Long artistId);
}
