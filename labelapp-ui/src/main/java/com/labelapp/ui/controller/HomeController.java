package com.recordslabel.labelapp.controllers;

import com.recordslabel.labelapp.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    ArtistServiceImpl artistService;

    AlbumServiceImpl albumService;

    SongServiceImpl songService;

    ProducerServiceImpl producerService;

    PrizeServiceImpl prizeService;

    public HomeController(ArtistServiceImpl artistService,
                          AlbumServiceImpl albumService,
                          SongServiceImpl songService,
                          ProducerServiceImpl producerService,
                          PrizeServiceImpl prizeService) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.songService = songService;
        this.producerService = producerService;
        this.prizeService = prizeService;
    }

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("artists", artistService.findAllArtists().size());
        model.addAttribute("albums", albumService.findAllAlbums());
        model.addAttribute("songs", songService.findAllSongs().size());
        model.addAttribute("producers", producerService.findAllProducers().size());
        model.addAttribute("prizes", prizeService.findAllPrizes());
        return "home";
    }
}
