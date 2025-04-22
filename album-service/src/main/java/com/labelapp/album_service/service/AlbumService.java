package com.labelapp.album_service.service;


import com.labelapp.album_service.dto.AlbumDTO;

import java.util.List;

public interface AlbumService {

    long findAllAlbums();
    List<AlbumDTO> findAlbumsByArtistId(Long id);
    AlbumDTO createAlbumForArtist(AlbumDTO albumDTO);

}
