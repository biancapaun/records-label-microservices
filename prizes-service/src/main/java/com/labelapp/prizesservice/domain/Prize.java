package com.labelapp.prizesservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "premiu")
@Getter
@Setter
@NoArgsConstructor
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premiu")
    private Long id;

    private String nume;
    private String categorie;
    private Integer an;

    @Column(name = "id_artist")
    private Long artistId;
}
