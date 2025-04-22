package com.labelapp.album_service.feign;

import com.labelapp.album_service.dto.ArtistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "artist-service")
public interface ArtistClient {

    @GetMapping("/api/artists/{id}")
    ArtistDTO getArtistById(@PathVariable("id") Long artistId);
}
