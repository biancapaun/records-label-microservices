package com.labelapp.ui.controller;

import com.labelapp.ui.client.AlbumClient;
import com.labelapp.ui.dto.AlbumDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/album")
public class AlbumController {

    private final AlbumClient albumClient;

    public AlbumController(AlbumClient albumClient) {
        this.albumClient = albumClient;
    }

    @GetMapping("/create")
    public String showCreateAlbumForm(@RequestParam("artistId") Long artistId, Model model) {
        AlbumDTO album = new AlbumDTO();
        album.setArtistId(artistId);
        model.addAttribute("album", album);
        return "album/form";
    }

    @PostMapping("/create")
    public String createAlbum(@ModelAttribute("album") AlbumDTO albumDTO) {
        albumClient.createAlbum(albumDTO);
        return "redirect:/artist/details/" + albumDTO.getArtistId();
    }
}
