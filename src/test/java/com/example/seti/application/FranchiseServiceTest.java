package com.example.seti.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.seti.domain.Franchise;
import com.example.seti.infrastructure.entity.FranchiseEntity;
import com.example.seti.infrastructure.mapper.FranchiseMapper;
import com.example.seti.infrastructure.repository.FranchiseRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FranchiseServiceTest {

	@Mock
	private FranchiseRepository franchiseRepository;

	@InjectMocks
	private FranchiseService franchiseService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
    void testAddFranchise() {
        Franchise franchise = new Franchise();
        franchise.setName("Franchise 1");

        FranchiseEntity entity = FranchiseMapper.toEntity(franchise);

        when(franchiseRepository.save(any(FranchiseEntity.class)))
            .thenReturn(Mono.just(entity));

        Mono<Franchise> result = franchiseService.addFranchise(franchise);

        StepVerifier.create(result)
            .expectNextMatches(f -> f.getName().equals("Franchise 1"))
            .verifyComplete();

        verify(franchiseRepository, times(1)).save(any(FranchiseEntity.class));
    }

	
	@Test
    void testGetAll() {
        FranchiseEntity entity1 = new FranchiseEntity();
        entity1.setName("F1");
        FranchiseEntity entity2 = new FranchiseEntity();
        entity2.setName("F2");

        when(franchiseRepository.findAll())
            .thenReturn(Flux.just(entity1, entity2));

        Flux<Franchise> result = franchiseService.getAll();

        StepVerifier.create(result)
            .expectNextMatches(f -> f.getName().equals("F1"))
            .expectNextMatches(f -> f.getName().equals("F2"))
            .verifyComplete();

        verify(franchiseRepository, times(1)).findAll();
    }
	
	
}
