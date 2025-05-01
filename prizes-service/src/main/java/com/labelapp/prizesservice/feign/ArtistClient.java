package com.labelapp.prizesservice.feign;

import com.labelapp.prizesservice.dto.PrizeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "artist-service")
public interface ArtistClient {

    @GetMapping("/artists/{id}")
    PrizeDTO getArtistById(@PathVariable("id") Long artistId);
}
