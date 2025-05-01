package com.recordslabel.labelapp.services;

import com.recordslabel.labelapp.dtos.ContractDTO;

import java.util.List;

public interface ContractService {

    List<ContractDTO> findContractsById(Long id);

}
