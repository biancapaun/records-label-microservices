package com.labelapp.album_service.repository;

import com.labelapp.album_service.domain.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize, Long> {

    List<Prize> findByArtistId(Long artistId);
}
