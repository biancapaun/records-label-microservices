package com.labelapp.album_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlbumDTO {

    @Schema(hidden = true)
    private Long id;

    @NotNull(message = "Album title must not be null")
    @NotBlank(message = "Album title must be a non-empty string")
    private String title;

    private LocalDate releaseDate;

    private Long artistId;
    private GenreDTO genre;


}
