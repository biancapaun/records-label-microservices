package com.labelapp.contractservice.repository;

import com.labelapp.contractservice.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByArtistId(Long artistId);
}
