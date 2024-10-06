package com.etraveli.api.clearing_cost.controller;

import com.etraveli.api.clearing_cost.dto.crud.ClearingCostDto;
import com.etraveli.api.clearing_cost.service.ClearingCostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = ClearingCostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ClearingCostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClearingCostService clearingCostService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void createClearingCostTest() throws Exception {

        ClearingCostDto dto = ClearingCostDto.builder()
                .countryName("Greece")
                .isoCode("GR")
                .cost(15)
                .build();

        when(clearingCostService.createClearingCost(ArgumentMatchers.any())).thenReturn(dto);

        ResultActions response = mockMvc.perform(post("/api/clearing-cost/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        response
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateClearingCostTest() throws Exception {

        ClearingCostDto dto = ClearingCostDto.builder()
                .countryName("Greece")
                .isoCode("GR")
                .cost(15)
                .build();

        when(clearingCostService.updateClearingCost(ArgumentMatchers.any())).thenReturn(dto);

        ResultActions response = mockMvc.perform(put("/api/clearing-cost/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        response
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findClearingCostByIdTest() throws Exception {

        ClearingCostDto dto = ClearingCostDto.builder()
                .countryName("Greece")
                .isoCode("GR")
                .cost(15)
                .build();

        when(clearingCostService.getClearingCostById(ArgumentMatchers.any())).thenReturn(dto);

        ResultActions response = mockMvc.perform(get("/api/clearing-cost/1")
                .contentType(MediaType.APPLICATION_JSON));

        response
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testFindClearingCostTest() throws Exception {

        ClearingCostDto dto = ClearingCostDto.builder()
                .countryName("Greece")
                .isoCode("GR")
                .cost(15)
                .build();

        when(clearingCostService.getAllClearingCosts()).thenReturn(List.of(dto));

        ResultActions response = mockMvc.perform(get("/api/clearing-cost/all")
                .contentType(MediaType.APPLICATION_JSON));

        response
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteClearingCostTest() throws Exception {

        doNothing().when(clearingCostService).deleteClearingCost(ArgumentMatchers.any());

        ResultActions response = mockMvc.perform(delete("/api/clearing-cost/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}