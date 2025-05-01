package com.labelapp.ui.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class PrizeDTO {
    private Long id;

    private String name;

    private String category;

    private String year;

    private Long artistId;


}
