package com.labelapp.artistservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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


}
