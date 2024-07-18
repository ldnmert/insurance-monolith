package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
