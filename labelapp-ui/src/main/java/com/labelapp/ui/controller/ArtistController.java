package com.labelapp.ui.controller;

import com.labelapp.ui.client.*;
import com.labelapp.ui.dto.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistClient artistClient;
    private final AlbumClient albumClient;
    private final PrizeClient prizeClient;
    private final ContractClient contractClient;
//    private final SongClient songClient;

    public ArtistController(ArtistClient artistClient, AlbumClient albumClient,
                            PrizeClient prizeClient, ContractClient contractClient) {
        this.artistClient = artistClient;
        this.albumClient = albumClient;
        this.prizeClient = prizeClient;
        this.contractClient = contractClient;
//        this.songClient = songClient;
    }

    @GetMapping("")
    public String artistList(@RequestParam(defaultValue = "asc") String sortDir, Model model) {
        /*List<ArtistDTO> artists = sortDir.equals("desc")
                ? artistClient.getAllArtistsSortedDesc()
                : artistClient.getAllArtistsSortedAsc();

*/
        List<ArtistDTO> artists = artistClient.getAllArtists(sortDir);
        model.addAttribute("artists", artists);
        model.addAttribute("sortDir", sortDir);
        return "artist/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("artist", new ArtistDTO());
        return "artist/form";
    }

    @PostMapping("/create")
    public String createArtist(@Valid @ModelAttribute("artist") ArtistDTO artistDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "artist/form";
        artistClient.createArtist(artistDto);
        return "redirect:/artist";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ArtistDTO artist = artistClient.getArtistById(id);
        model.addAttribute("artist", artist);
        return "artist/form";
    }

    @PostMapping("/edit/{id}")
    public String updateArtist(@PathVariable Long id, @ModelAttribute("artist") ArtistDTO artistDto) {
        artistClient.updateArtist(id, artistDto);
        return "redirect:/artist";
    }

    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Long id) {
        artistClient.deleteArtist(id);
        return "redirect:/artist";
    }

    @GetMapping("/details/{id}")
    public String showArtistDetails(@PathVariable Long id, Model model) {
        ArtistDTO artist = artistClient.getArtistById(id);
        List<AlbumDTO> albums = albumClient.getAlbumsByArtist(id);
        List<PrizeDTO> prizes = prizeClient.getPrizesByArtist(id);
        List<ContractDTO> contracts = contractClient.getContractsByArtist(id);

//        albums.forEach(album -> {
//            List<SongDTO> songs = songClient.getSongsByAlbum(album.getId());
//            album.setSongs(songs);
//        });

        model.addAttribute("artist", artist);
        model.addAttribute("albums", albums);
        model.addAttribute("prizes", prizes);
        model.addAttribute("contracts", contracts);
//        model.addAttribute("newSong", new SongDTO());

        return "artist/details";
    }
}
