package com.example.seti.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.seti.infrastructure.entity.ProductEntity;

import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {

	Flux<ProductEntity> findByBranchId(Long branchId);
	
}
