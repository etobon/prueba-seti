package com.example.seti.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.seti.infrastructure.entity.BranchEntity;

import reactor.core.publisher.Flux;

public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, Long> {
	
	Flux<BranchEntity> findByFranchiseId(Long franchiseId);
	
}
