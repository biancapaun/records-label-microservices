package com.labelapp.contractservice.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "label")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Label {

    @Id
    @Column(name="id_label")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nume_label")
    private String labelName;

    @Column(name="tara_origine")
    private String originCountry;

    @Column(name="an_infiintare")
    private Integer foundedYear;

}
