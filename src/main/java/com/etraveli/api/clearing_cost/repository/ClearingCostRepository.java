package com.etraveli.api.clearing_cost.repository;

import com.etraveli.api.clearing_cost.model.ClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClearingCostRepository extends JpaRepository<ClearingCost, Long> {

    Optional<ClearingCost> findByIsoCode(String isoCode);
}