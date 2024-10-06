package com.etraveli.api.clearing_cost.dto.crud;

import com.etraveli.api.shared.BaseEntityDto;
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
public class ClearingCostDto extends BaseEntityDto {

    @NotNull
    private String countryName;

    @NotNull
    private String isoCode;

    @NotNull
    private Integer cost;
}
