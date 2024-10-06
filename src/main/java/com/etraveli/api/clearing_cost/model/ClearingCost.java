package com.etraveli.api.clearing_cost.model;

import com.etraveli.api.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clearing_cost")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ClearingCost extends BaseEntity {

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    @Column(name = "iso_code", nullable = false, unique = true)
    private String isoCode;

    @Column(name = "clearing_cost", nullable = false)
    private Integer cost;
}
