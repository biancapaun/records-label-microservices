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
@RequestMapping("/producer")
public class ProducerController {

//    private final ArtistClient artistClient;
//    private final AlbumClient albumClient;
//    private final PrizeClient prizeClient;
//    private final ContractClient contractClient;
    private final ProducerClient producerClient;
    private final ProductionClient productionClient;
//    private final SongClient songClient;

    public ProducerController(ProducerClient producerClient, ProductionClient productionClient) {
//        this.artistClient = artistClient;
//        this.albumClient = albumClient;
//        this.prizeClient = prizeClient;
//        this.contractClient = contractClient;
//        this.songClient = songClient;
        this.producerClient = producerClient;
        this.productionClient = productionClient;
    }

    @GetMapping("")
    public String producerList(@RequestParam(defaultValue = "asc") String sortDir, Model model) {
        /*List<ArtistDTO> artists = sortDir.equals("desc")
                ? artistClient.getAllArtistsSortedDesc()
                : artistClient.getAllArtistsSortedAsc();

*/
        List<ProducerDTO> producers = producerClient.getAllProducers(sortDir);
        model.addAttribute("producers", producers);
        model.addAttribute("sortDir", sortDir);
        return "producer/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("producer", new ProducerDTO());
        return "producer/form";
    }

    @PostMapping("/create")
    public String createProducer(@Valid @ModelAttribute("producer")ProducerDTO producerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "producer/form";
        producerClient.createProducer(producerDTO);
        return "redirect:/producer";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProducerDTO producer = producerClient.getProducerById(id);
        model.addAttribute("producer", producer);
        return "producer/form";
    }

    @PostMapping("/edit/{id}")
    public String updateProducer(@PathVariable Long id, @ModelAttribute("producer") ProducerDTO producerDTO) {
        producerClient.updateProducer(id, producerDTO);
        return "redirect:/producer";
    }

    @GetMapping("/delete/{id}")
    public String deleteProducer(@PathVariable Long id) {
        producerClient.deleteProducer(id);
        return "redirect:/producer";
    }

    @GetMapping("/details/{id}")
    public String showProducerDetails(@PathVariable Long id, Model model) {
        ProducerDTO producer = producerClient.getProducerById(id);
        model.addAttribute("producer", producer);
        List<ProductionDTO> productions = productionClient.getProductionsByProducerId(id);
        model.addAttribute("productions", productions);

        return "producer/details";
    }
}
