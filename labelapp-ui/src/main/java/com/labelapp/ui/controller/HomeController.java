package com.labelapp.ui.controller;

import com.labelapp.ui.client.AlbumClient;
import com.labelapp.ui.client.ArtistClient;
import com.labelapp.ui.client.PrizeClient;
import com.labelapp.ui.client.ProducerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

   AlbumClient albumClient;
   ArtistClient artistClient;
   PrizeClient prizeClient;
   ProducerClient producerClient;

    public HomeController(AlbumClient albumClient, ArtistClient artistClient,
                          PrizeClient prizeClient,ProducerClient producerClient ) {
        this.albumClient = albumClient;
        this.artistClient = artistClient;
        this.prizeClient = prizeClient;
        this.producerClient = producerClient;
    }

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("albums", albumClient.countAlbums());
        model.addAttribute("artists", artistClient.countArtists());
        model.addAttribute("prizes", prizeClient.countPrizes());
        model.addAttribute("producers", producerClient.countProducers());
        return "home";
    }
}
