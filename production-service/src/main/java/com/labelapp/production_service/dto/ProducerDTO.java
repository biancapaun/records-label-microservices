package com.labelapp.production_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDTO {

    private Long id;

    @NotNull(message = "Name must not be null")
    @NotBlank(message = "Name must be a non-empty string")
    private String name;

    private String specialization;

    private String originCountry;


}
