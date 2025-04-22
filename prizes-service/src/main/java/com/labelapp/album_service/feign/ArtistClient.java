package com.labelapp.album_service.feign;

import com.labelapp.album_service.dto.PrizeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "artist-service")
public interface ArtistClient {

    @GetMapping("/api/artists/{id}")
    PrizeDTO getArtistById(@PathVariable("id") Long artistId);
}
