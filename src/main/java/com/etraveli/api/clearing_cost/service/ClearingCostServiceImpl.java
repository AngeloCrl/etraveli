package com.etraveli.api.clearing_cost.service;

import com.etraveli.api.clearing_cost.dto.crud.ClearingCostDto;
import com.etraveli.api.clearing_cost.dto.crud.CreateUpdateClearingCostDto;
import com.etraveli.api.clearing_cost.dto.request.RequestDto;
import com.etraveli.api.clearing_cost.dto.request.ResponseDto;
import com.etraveli.api.clearing_cost.model.ClearingCost;
import com.etraveli.api.clearing_cost.repository.ClearingCostRepository;
import com.etraveli.api.utils.AppUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClearingCostServiceImpl implements ClearingCostService {

    private final ClearingCostRepository clearingCostRepository;
    private ModelMapper strictModelMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClearingCostServiceImpl.class);
    @Value("${binlist.url}")
    private String binlistUrl;

    @PostConstruct
    private void setStrictModelMapper() {
        strictModelMapper = AppUtils.initStrictModelMapper();
    }

    @Override
    public ClearingCostDto createClearingCost(CreateUpdateClearingCostDto dto) {
        LOGGER.info("Creating Clearing Cost");

        ClearingCost clearingCost = clearingCostRepository.save(strictModelMapper.map(dto, ClearingCost.class));
        return strictModelMapper.map(clearingCost, ClearingCostDto.class);
    }

    @Override
    public ClearingCostDto updateClearingCost(CreateUpdateClearingCostDto dto) {
        LOGGER.info("Updating Clearing Cost");

        ClearingCost clearingCost = clearingCostRepository.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Clearing Cost with id: " + dto.getId()));

        strictModelMapper.map(dto, clearingCost);
        clearingCostRepository.save(clearingCost);

        return strictModelMapper.map(clearingCost, ClearingCostDto.class);
    }

    @Override
    public ClearingCostDto getClearingCostById(Long id) {
        LOGGER.info("Getting Clearing Cost by id");

        ClearingCost clearingCost = clearingCostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Clearing Cost with id: " + id));

        return strictModelMapper.map(clearingCost, ClearingCostDto.class);
    }

    @Override
    public List<ClearingCostDto> getAllClearingCosts() {
        LOGGER.info("Getting Clearing all Costs");

        List<ClearingCost> clearingCosts = clearingCostRepository.findAll();
        return clearingCosts.stream().map(clearingCost -> strictModelMapper.map(clearingCost, ClearingCostDto.class)).toList();
    }

    @Override
    public void deleteClearingCost(Long id) {
        LOGGER.info("Deleting Clearing Cost by id");

        ClearingCost clearingCost = clearingCostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Clearing Cost with id: " + id));
        try {
            clearingCostRepository.delete(clearingCost);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete Clearing Cost with id: " + id, e);
        }

    }

    @Override
    public ResponseDto getInfoFromBinListApi(RequestDto dto) {
        ResponseDto responseDto = new ResponseDto();
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();


        try {
            headers.set("Accept-Version", "3");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(binlistUrl + dto.getCardNumber(), HttpMethod.GET, entity, String.class);

            JsonNode rootNode = objectMapper.readTree(String.valueOf(response.getBody()));
            JsonNode countryNode = rootNode.path("country");
            String isoCode = countryNode.path("alpha2").asText();

            Optional<ClearingCost> clearingCost = clearingCostRepository.findByIsoCode(isoCode);

            if (clearingCost.isPresent()) {
                responseDto.setCost(clearingCost.get().getCost());
            } else {
                responseDto.setCost(10);
            }
            responseDto.setCountry(isoCode);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "You have exceeded the allowed limit of requests.");
        }
        return responseDto;
    }

}
