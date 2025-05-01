package com.labelapp.ui.client;

import com.labelapp.ui.dto.ArtistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "artist-service")
public interface ArtistClient {

    @GetMapping("/artists/sorted/asc")
    List<ArtistDTO> getAllArtistsSortedAsc();

    @GetMapping("/artists/sorted/desc")
    List<ArtistDTO> getAllArtistsSortedDesc();

    @GetMapping("/artists")
    List<ArtistDTO> getAllArtists(@RequestParam(name = "sortDir", defaultValue = "asc") String sortDir);


    @PostMapping("/artists")
    void createArtist(@RequestBody ArtistDTO artistDTO);

    @GetMapping("/artists/{id}")
    ArtistDTO getArtistById(@PathVariable Long id);

    @PutMapping("/artists/{id}")
    void updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO);

    @DeleteMapping("/artists/{id}")
    void deleteArtist(@PathVariable Long id);

    @GetMapping("/artists/count")
    Long countArtists();
}