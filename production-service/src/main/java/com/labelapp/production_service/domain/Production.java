package com.labelapp.production_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "productie")
@IdClass(ProductionId.class)
@Getter
@Setter
public class Production {

    @Id
    @Column(name = "id_album")
    private Long albumId;

    @Id
    @Column(name = "id_producator")
    private Long producerId;

    @Column(name="tip_productie")
    private String productionType;

    @Column(name="buget")
    private BigDecimal budget;

}
