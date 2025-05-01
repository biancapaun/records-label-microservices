package com.labelapp.ui.client;


import com.labelapp.ui.dto.AlbumDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "album-service")
public interface AlbumClient {

    @GetMapping("/albums/artist/{artistId}")
    List<AlbumDTO> getAlbumsByArtist(@PathVariable Long artistId);
}
