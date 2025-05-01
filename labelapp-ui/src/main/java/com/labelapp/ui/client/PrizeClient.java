package com.labelapp.ui.client;

import com.labelapp.ui.dto.PrizeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "prizes-service")
public interface PrizeClient {

    @GetMapping("/prizes/artist/{artistId}")
    List<PrizeDTO> getPrizesByArtist(@PathVariable Long artistId);

    @GetMapping("/prizes/count")
    Long countPrizes();
}
