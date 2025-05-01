package com.labelapp.prizesservice.controller;

import com.labelapp.prizesservice.dto.PrizeDTO;
import com.labelapp.prizesservice.service.PrizeServiceImpl;
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

    @GetMapping("/count")
    public Long countPrizes(){
        return prizeService.countPrizes();
    }
}


