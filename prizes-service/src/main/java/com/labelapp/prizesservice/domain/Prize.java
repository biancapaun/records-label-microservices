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

    @Column(name = "nume")
    private String name;

    @Column(name = "categorie")
    private String category;

    @Column(name = "an")
    private Integer year;

    @Column(name = "id_artist")
    private Long artistId;
}
