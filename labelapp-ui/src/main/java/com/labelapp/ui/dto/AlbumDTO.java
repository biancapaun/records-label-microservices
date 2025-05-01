package com.labelapp.ui.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlbumDTO {

    private Long id;

    @NotNull(message = "Album title must not be null")
    @NotBlank(message = "Album title must be a non-empty string")
    private String title;

    private LocalDate releaseDate;

    private Long artistId;
    private GenreDTO genre;


}
