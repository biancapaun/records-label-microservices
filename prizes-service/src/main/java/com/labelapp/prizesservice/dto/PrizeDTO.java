package com.labelapp.prizesservice.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class PrizeDTO {

    private Long artistId;

    private Long id;

    private String name;

    private String category;

    private String year;

}
