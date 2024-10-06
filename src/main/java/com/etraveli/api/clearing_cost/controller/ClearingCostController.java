package com.etraveli.api.clearing_cost.controller;

import com.etraveli.api.clearing_cost.dto.crud.ClearingCostDto;
import com.etraveli.api.clearing_cost.dto.crud.CreateUpdateClearingCostDto;
import com.etraveli.api.clearing_cost.dto.request.RequestDto;
import com.etraveli.api.clearing_cost.dto.request.ResponseDto;
import com.etraveli.api.clearing_cost.service.ClearingCostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearing-cost")
@AllArgsConstructor
public class ClearingCostController {

    private ClearingCostService clearingCostService;


    @PostMapping("/")
    public ResponseEntity<ClearingCostDto> createClearingCost(
            @RequestBody @Valid CreateUpdateClearingCostDto dto
    ) {
        ClearingCostDto clearingCost = clearingCostService.createClearingCost(dto);
        return new ResponseEntity<>(clearingCost, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<ClearingCostDto> updateClearingCost(
            @RequestBody @Valid CreateUpdateClearingCostDto dto
    ) {
        ClearingCostDto clearingCost = clearingCostService.updateClearingCost(dto);
        return new ResponseEntity<>(clearingCost, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClearingCostDto> findClearingCostById(
            @PathVariable Long id
    ) {
        ClearingCostDto clearingCost = clearingCostService.getClearingCostById(id);
        return new ResponseEntity<>(clearingCost, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClearingCostDto>> findClearingCost() {
        List<ClearingCostDto> clearingCosts = clearingCostService.getAllClearingCosts();
        return new ResponseEntity<>(clearingCosts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteClearingCost(
            @PathVariable Long id
    ) {
        clearingCostService.deleteClearingCost(id);
    }

    @GetMapping("/binlist")
    public ResponseDto getInfoFromBinListApi(
            @RequestBody @Valid RequestDto dto
    ) {
        return clearingCostService.getInfoFromBinListApi(dto);
    }
}
