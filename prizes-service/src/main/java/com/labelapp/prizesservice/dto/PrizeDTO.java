package com.labelapp.prizesservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PrizeDTO {

    private Long id;
    private String nume;
    private String categorie;
    private Integer an;
    private Long artistId;

}
