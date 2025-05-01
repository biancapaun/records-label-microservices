package com.labelapp.artistservice.controller;

import com.labelapp.artistservice.dto.ArtistDTO;
import com.labelapp.artistservice.service.ArtistServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistServiceImpl artistService;

    public ArtistController(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }


    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists(
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        List<ArtistDTO> artists = sortDir.equalsIgnoreCase("desc")
                ? artistService.findAllArtistsSortedDesc()
                : artistService.findAllArtistsSortedAsc();
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.findArtistById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createArtist(@Valid @RequestBody ArtistDTO artistDTO) {
        artistService.createArtist(artistDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
        artistService.updateArtist(id, artistDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtistById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public Long count() {
        return artistService.countArtists();
    }
}

