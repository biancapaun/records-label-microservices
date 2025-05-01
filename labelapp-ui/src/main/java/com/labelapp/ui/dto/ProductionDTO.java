package com.labelapp.ui.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionDTO {

    private ProductionId id;

    @NotNull(message = "Production type must not be null")
    @NotBlank(message = "Production type be a non-empty string")
    private String productionType;

    private BigDecimal budget;

    private Long producerId;

}
