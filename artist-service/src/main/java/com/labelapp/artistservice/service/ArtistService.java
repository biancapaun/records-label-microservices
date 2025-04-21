package com.labelapp.artistservice.service;


import com.labelapp.artistservice.dto.ArtistDTO;

import java.util.List;

public interface ArtistService {

    List<ArtistDTO> findAllArtists();
    ArtistDTO findArtistById(Long id);
    void createArtist(ArtistDTO artistDTO);
    void updateArtist(Long id, ArtistDTO artistDTO);
    void deleteArtistById(Long id);
    List<ArtistDTO> findAllArtistsSortedAsc();
    List<ArtistDTO> findAllArtistsSortedDesc();




}
