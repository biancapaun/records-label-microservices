package com.labelapp.ui.client;

import com.labelapp.ui.dto.ContractDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "contract-service")
public interface ContractClient {

    @GetMapping("/contracts/artist/{artistId}")
    List<ContractDTO> getContractsByArtist(@PathVariable("artistId")Long id);

}
