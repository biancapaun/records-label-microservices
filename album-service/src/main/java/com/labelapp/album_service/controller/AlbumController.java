package com.labelapp.album_service.controller;

import com.labelapp.album_service.dto.AlbumDTO;
import com.labelapp.album_service.service.AlbumServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    @Autowired
    private AlbumServiceImpl albumService;

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtist(@PathVariable Long artistId) {
        List<AlbumDTO> albums = albumService.findAlbumsByArtistId(artistId);
        return ResponseEntity.ok(albums);
    }

    @PostMapping
    public ResponseEntity<Void> createAlbum(@RequestBody @Valid AlbumDTO albumDTO) {
        albumService.createAlbumForArtist(albumDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAlbums() {
        return ResponseEntity.ok(albumService.findAllAlbums());
    }
}


