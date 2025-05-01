package com.recordslabel.labelapp.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

//    private long id;
    @NotNull(message = "Genre must not be null")
    @NotBlank(message = "Genre must be a non-empty string")
    private String genreName;

}
