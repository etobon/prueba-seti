package com.example.seti.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.seti.infrastructure.entity.FranchiseEntity;

public interface FranchiseRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {

}
