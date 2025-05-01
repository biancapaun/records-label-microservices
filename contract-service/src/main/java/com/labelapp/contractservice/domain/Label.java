package com.recordslabel.labelapp.entities;

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

    @OneToMany(mappedBy = "label", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_adrese")
    private Address address;
}
