package com.labelapp.ui.client;


import com.labelapp.ui.dto.AlbumDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "album-service")
public interface AlbumClient {

    @PostMapping("/albums")
    AlbumDTO createAlbum(@RequestBody AlbumDTO albumDTO);

    @GetMapping("/albums/artist/{artistId}")
    List<AlbumDTO> getAlbumsByArtist(@PathVariable Long artistId);

    @GetMapping("/albums/count")
    Long countAlbums();

}
