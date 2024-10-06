package com.etraveli.api.clearing_cost.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class RequestDto {

    @NotNull
    @Size(max = 19, min = 8, message = "Card number should be consisted by min 8 to max 19 digits")
    Long cardNumber;
}
