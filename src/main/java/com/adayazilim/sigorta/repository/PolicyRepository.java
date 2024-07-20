package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByPolicyNumber(String policyNumber);

    Optional<Policy> findByPolicyNumber(String policyNumber);
    List<Policy> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);

    List<Policy> findByCustomerIdAndUserId(Long customerId, Long userId);
}
