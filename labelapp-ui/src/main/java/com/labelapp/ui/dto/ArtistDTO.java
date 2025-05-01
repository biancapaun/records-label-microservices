package com.labelapp.ui.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtistDTO {


    private Long id;

    @NotNull(message = "sceneName must not be null")
    @NotBlank(message = "sceneName must be a non-empty string")
    private String sceneName;


    @NotNull(message = "realName must not be null")
    @NotBlank(message = "realName must be a non-empty string")
    private String realName;

    @Past(message = "dateOfBirth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "originCountry must not be null")
    @NotBlank(message = "originCountry must be a non-empty string")
    private String originCountry;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistDTO artistDTO = (ArtistDTO) o;
        return Objects.equals(id, artistDTO.id) && Objects.equals(sceneName, artistDTO.sceneName) && Objects.equals(realName, artistDTO.realName) && Objects.equals(dateOfBirth, artistDTO.dateOfBirth) && Objects.equals(originCountry, artistDTO.originCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sceneName, realName, dateOfBirth, originCountry);
    }


}
