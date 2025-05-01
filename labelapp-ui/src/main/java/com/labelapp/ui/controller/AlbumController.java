package com.recordslabel.labelapp.controllers;


import com.recordslabel.labelapp.dtos.AlbumDTO;
import com.recordslabel.labelapp.services.AlbumServiceImpl;
import com.recordslabel.labelapp.services.ArtistServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/album")
public class AlbumController {

    AlbumServiceImpl albumService;
    ArtistServiceImpl artistService;

    public AlbumController(ArtistServiceImpl artistService, AlbumServiceImpl albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping("/create")
    public String showCreateForm(@RequestParam("artistId") Long artistId, Model model) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setArtistId(artistId);
        model.addAttribute("album", albumDTO);
        return "album/form";
    }

    @PostMapping("/create")
    public String createAlbum(@Valid @ModelAttribute("album") AlbumDTO albumDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "album/form";
        }
        albumService.createAlbumForArtist(albumDto);
        return "redirect:/artist/details/" + albumDto.getArtistId();
    }
}
