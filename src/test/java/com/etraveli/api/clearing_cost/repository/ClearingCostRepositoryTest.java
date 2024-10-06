package com.etraveli.api.clearing_cost.repository;

import com.etraveli.api.clearing_cost.model.ClearingCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ClearingCostRepositoryTest {

    @Autowired
    ClearingCostRepository clearingCostRepository;

    @Test
    void findByIsoCode() {

        ClearingCost clearingCost = ClearingCost.builder()
                .countryName("France")
                .isoCode("FR")
                .cost(15)
                .build();

        clearingCostRepository.save(clearingCost);
        Optional<ClearingCost> fr = clearingCostRepository.findByIsoCode("FR");

        assertNotNull(fr);
        assertTrue(fr.isPresent());
        assertEquals(fr.get().getIsoCode(), "FR");

    }
}