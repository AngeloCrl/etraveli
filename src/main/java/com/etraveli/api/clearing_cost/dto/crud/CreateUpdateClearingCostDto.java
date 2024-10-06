package com.etraveli.api.clearing_cost.dto.crud;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateUpdateClearingCostDto {

    private Long id;

    @NotNull
    private String countryName;

    @NotNull
    private String isoCode;

    @NotNull
    private Integer cost;
}
