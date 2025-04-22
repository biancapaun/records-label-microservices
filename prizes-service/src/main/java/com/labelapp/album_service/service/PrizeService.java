package com.labelapp.album_service.service;


import com.labelapp.album_service.dto.PrizeDTO;

import java.util.List;

public interface PrizeService {

    List<PrizeDTO> findPrizesByArtistId(Long artistId);

}
