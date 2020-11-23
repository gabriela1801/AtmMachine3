package com.example.AtmMachine.config;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientDetailsRepository extends CrudRepository<ClientDetailsJpaEntity, Long> {
    List<ClientDetailsJpaEntity> findByLastName(String lastName);

    ClientDetailsJpaEntity findByCardNumber(String cardNumber);

    List<ClientDetailsJpaEntity> findAll();

    ClientDetailsJpaEntity findById(long id);
}
