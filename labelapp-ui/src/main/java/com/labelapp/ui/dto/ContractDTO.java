package com.labelapp.contractservice.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractDTO {

    private long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LabelDTO label;
}
