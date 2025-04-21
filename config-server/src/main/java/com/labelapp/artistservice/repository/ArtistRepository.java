package com.labelapp.artistservice.repository;

import com.labelapp.artistservice.domain.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findAllByOrderByIdAsc();

    Page<Artist> findAll(Pageable pageable);
    List<Artist> findAllByOrderBySceneNameAsc();
    List<Artist> findAllByOrderBySceneNameDesc();

}
