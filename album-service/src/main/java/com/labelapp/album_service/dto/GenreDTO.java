package com.labelapp.album_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

    @NotNull(message = "Genre must not be null")
    @NotBlank(message = "Genre must be a non-empty string")
    private String genreName;

    public @NotNull(message = "Genre must not be null") @NotBlank(message = "Genre must be a non-empty string") String getGenreName() {
        return genreName;
    }

    public void setGenreName(@NotNull(message = "Genre must not be null") @NotBlank(message = "Genre must be a non-empty string") String genreName) {
        this.genreName = genreName;
    }
}
