package com.labelapp.contractservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "contract")
@Getter
@Setter
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private Long id;

    @Column(name="data_start")
    private LocalDate startDate;

    @Column(name="data_incheiere")
    private LocalDate endDate;

    @Column(name = "id_artist")
    private Long artistId;


}
