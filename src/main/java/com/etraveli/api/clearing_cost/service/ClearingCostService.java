package com.etraveli.api.clearing_cost.service;

import com.etraveli.api.clearing_cost.dto.crud.ClearingCostDto;
import com.etraveli.api.clearing_cost.dto.crud.CreateUpdateClearingCostDto;
import com.etraveli.api.clearing_cost.dto.request.RequestDto;
import com.etraveli.api.clearing_cost.dto.request.ResponseDto;

import java.util.List;

public interface ClearingCostService {
    ClearingCostDto createClearingCost(CreateUpdateClearingCostDto dto);

    ClearingCostDto updateClearingCost(CreateUpdateClearingCostDto dto);

    ClearingCostDto getClearingCostById(Long id);

    List<ClearingCostDto> getAllClearingCosts();

    void deleteClearingCost(Long id);

    ResponseDto getInfoFromBinListApi(RequestDto dto);
}

