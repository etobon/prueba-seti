package com.example.seti.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seti.domain.Franchise;
import com.example.seti.infrastructure.mapper.FranchiseMapper;
import com.example.seti.infrastructure.repository.FranchiseRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranchiseService {
	
	@Autowired
	private FranchiseRepository franchiseRepository;
	
	public Mono<Franchise> addFranchise(Franchise f) {
        return franchiseRepository.save(FranchiseMapper.toEntity(f))
                   .map(FranchiseMapper::toDomain)
                   .doOnNext(fr -> System.out.println("Saved franchise: " + fr.getName()));
    }
	
	
	public Flux<Franchise> getAll() {
        return franchiseRepository.findAll().map(FranchiseMapper::toDomain);
    }
	
}
