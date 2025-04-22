package com.labelapp.prizesservice.service;


import com.labelapp.prizesservice.dto.PrizeDTO;

import java.util.List;

public interface PrizeService {

    List<PrizeDTO> findPrizesByArtistId(Long artistId);

}
