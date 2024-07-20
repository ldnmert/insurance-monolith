package com.adayazilim.sigorta.repository;

import com.adayazilim.sigorta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByUsersId(Long userId);

    Optional<Customer> findByIdentificationNumber(String identificationNumber);

    List<Customer> findTop10ByUsersIdOrderByCreatedAtDesc(Long userId);
}
