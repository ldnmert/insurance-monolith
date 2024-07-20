package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByPolicyNumber(String policyNumber);

    List<Policy> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);
}
