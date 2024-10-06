package com.etraveli.api.clearing_cost.service;

import com.etraveli.api.clearing_cost.dto.crud.ClearingCostDto;
import com.etraveli.api.clearing_cost.dto.crud.CreateUpdateClearingCostDto;
import com.etraveli.api.clearing_cost.model.ClearingCost;
import com.etraveli.api.clearing_cost.repository.ClearingCostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ClearingCostServiceImplTest {

    @Autowired
    ClearingCostServiceImpl clearingCostService;

    @Autowired
    ClearingCostRepository clearingCostRepository;

    @Test
    void createClearingCostTest() {
        //Given
        CreateUpdateClearingCostDto dto = CreateUpdateClearingCostDto.builder()
                .countryName("United Kingdom")
                .isoCode("UK")
                .cost(15)
                .build();

        //When
        ClearingCostDto clearingCost = clearingCostService.createClearingCost(dto);

        //Then
        assertNotNull(clearingCost);
        assertNotNull(clearingCost.getId());
        assertEquals(clearingCost.getCountryName(), "United Kingdom");
        assertEquals(clearingCost.getIsoCode(), "UK");
        assertEquals(clearingCost.getCost(), 15);
    }

    @Test
    void updateClearingCostTest() {
        //Given
        CreateUpdateClearingCostDto dto = CreateUpdateClearingCostDto.builder()
                .countryName("Spain")
                .isoCode("ESP")
                .cost(15)
                .build();

        ClearingCostDto createdClearingCost = clearingCostService.createClearingCost(dto);

        dto.setId(createdClearingCost.getId());
        dto.setCountryName("Spain");
        dto.setIsoCode("ESP");

        //When
        ClearingCostDto updatedClearingCost = clearingCostService.updateClearingCost(dto);

        //Then
        assertNotNull(updatedClearingCost);
        assertEquals(updatedClearingCost.getId(), createdClearingCost.getId());
        assertEquals(updatedClearingCost.getCountryName(), "Spain");
        assertEquals(updatedClearingCost.getIsoCode(), "ESP");
        assertEquals(updatedClearingCost.getCost(), 15);
    }

    @Test
    void getClearingCostByIdTest() {
        //Given
        CreateUpdateClearingCostDto dto = CreateUpdateClearingCostDto.builder()
                .countryName("Greece")
                .isoCode("GR")
                .cost(15)
                .build();

        ClearingCostDto createdClearingCost = clearingCostService.createClearingCost(dto);

        dto.setId(createdClearingCost.getId());

        //When
        ClearingCostDto updatedClearingCost = clearingCostService.getClearingCostById(dto.getId());

        //Then
        assertNotNull(updatedClearingCost);
        assertEquals(updatedClearingCost.getId(), createdClearingCost.getId());
        assertEquals(updatedClearingCost.getCountryName(), "Greece");
        assertEquals(updatedClearingCost.getIsoCode(), "GR");
        assertEquals(updatedClearingCost.getCost(), 15);
    }

    @Test
    void getAllClearingCostsTest() {
        //Given
        CreateUpdateClearingCostDto dto1 = CreateUpdateClearingCostDto.builder()
                .countryName("Italy")
                .isoCode("IT")
                .cost(15)
                .build();

        clearingCostService.createClearingCost(dto1);

        //When
        List<ClearingCostDto> clearingCosts = clearingCostService.getAllClearingCosts();

        //Then
        assertNotNull(clearingCosts);
        assertFalse(clearingCosts.isEmpty());
        assertTrue(clearingCosts.stream().anyMatch(c -> c.getCountryName().equals("Italy")));
    }

    @Test
    void deleteClearingCostTest() {
        //Given
        CreateUpdateClearingCostDto dto = CreateUpdateClearingCostDto.builder()
                .countryName("Greece")
                .isoCode("GR")
                .cost(15)
                .build();

        ClearingCostDto createdClearingCost = clearingCostService.createClearingCost(dto);

        dto.setId(createdClearingCost.getId());

        //When
        clearingCostService.deleteClearingCost(dto.getId());

        Optional<ClearingCost> clearingCost = clearingCostRepository.findById(dto.getId());

        //Then
        assertTrue(clearingCost.isEmpty());
    }
}