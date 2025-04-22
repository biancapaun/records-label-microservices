package com.labelapp.album_service.controller;

import com.labelapp.album_service.dto.PrizeDTO;
import com.labelapp.album_service.service.PrizeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prizes")
@RequiredArgsConstructor
public class PrizeController {

    private final PrizeServiceImpl prizeService;

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<PrizeDTO>> getPrizesByArtist(@PathVariable Long artistId) {
        List<PrizeDTO> prizes = prizeService.findPrizesByArtistId(artistId);
        return ResponseEntity.ok(prizes);
    }
}


