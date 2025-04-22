package com.labelapp.prizesservice.repository;

import com.labelapp.prizesservice.domain.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize, Long> {

    List<Prize> findByArtistId(Long artistId);
}
