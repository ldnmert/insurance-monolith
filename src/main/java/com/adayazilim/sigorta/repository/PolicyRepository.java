package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.Customer;
import com.adayazilim.sigorta.entity.Policy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByPolicyNumber(String policyNumber);

    Optional<Policy> findByPolicyNumber(String policyNumber);
    List<Policy> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);

    List<Policy> findByCustomerIdAndUserId(Long customerId, Long userId);

    List<Policy> findTop10ByOrderByCreatedAtDesc();

    List<Policy> findByStatus(char status);
    List<Policy> findByUserId(Long userId);

    List<Policy> findByUserIdAndStatus(Long userId, char status, Sort sort);

    List<Policy> findByStatus(char status, Sort sort);

    List<Policy> findByUserId(Long userId, Sort sort);

    @Query("SELECT p FROM Policy p WHERE p.user.id = :userId AND p.endDate <= :endDate")
    List<Policy> findExpiringPoliciesByUserId(@Param("userId") Long userId, @Param("endDate") LocalDate endDate);

    @Query("SELECT p FROM Policy p WHERE p.endDate <= :endDate")
    List<Policy> findExpiringPolicies(@Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(p) FROM Policy p WHERE p.user.id = :userId AND p.status = 'K'")
    long countPoliciesByUserIdAndStatusK(@Param("userId") Long userId);

    @Query("SELECT COUNT(p) FROM Policy p WHERE p.user.id = :userId")
    long countPoliciesByUserId(@Param("userId") Long userId);


    List<Policy> findTop3ByUserIdOrderByAmountDesc(Long userId);

    @Query("SELECT DISTINCT p.customer FROM Policy p WHERE p.user.id = :userId")
    List<Customer> findDistinctCustomersByUserId(@Param("userId") Long userId);

    List<Policy> findByCustomerId(Long customerId);
}
