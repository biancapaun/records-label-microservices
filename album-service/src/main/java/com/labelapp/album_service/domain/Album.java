package com.labelapp.album_service.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "album")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Album {

    @Id
    @Column(name="id_album")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titlu")
    private String title;

    @Column(name="data_lansare")
    private LocalDate releaseDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_gen")
    private Genre genre;

    @Column(name = "id_artist")
    private Long artistId;


}
