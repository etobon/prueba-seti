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

import com.example.seti.domain.Branch;
import com.example.seti.infrastructure.entity.BranchEntity;
import com.example.seti.infrastructure.entity.ProductEntity;
import com.example.seti.infrastructure.mapper.BranchMapper;
import com.example.seti.infrastructure.repository.BranchRepository;
import com.example.seti.infrastructure.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class BranchServiceTest {

	@Mock
    private BranchRepository branchRepo;

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private BranchService branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBranch() {
        Branch branch = new Branch();
        branch.setName("Branch 1");

        BranchEntity savedBranchEntity = BranchMapper.toEntity(branch);
        savedBranchEntity.setId(1L); // simula el ID asignado al guardar

        ProductEntity p1 = new ProductEntity();
        p1.setId(10L);
        p1.setName("Product 1");
        ProductEntity p2 = new ProductEntity();
        p2.setId(11L);
        p2.setName("Product 2");

        when(branchRepo.save(any(BranchEntity.class))).thenReturn(Mono.just(savedBranchEntity));
        when(productRepo.findByBranchId(savedBranchEntity.getId())).thenReturn(Flux.just(p1, p2));

        Mono<Branch> result = branchService.addBranch(100L, branch);

//        StepVerifier.create(result)
//            .expectNextMatches(b -> 
//                b.getName().equals("Branch 1") &&
//                b.getFranchiseId().equals(100L) &&
//                b.getProducts().size() == 2
//            )
//            .verifyComplete();

        verify(branchRepo, times(1)).save(any(BranchEntity.class));
        //verify(productRepo, times(1)).findByBranchId(savedBranchEntity.getId());
    }

    @Test
    void testUpdateBranchName() {
        BranchEntity existingBranch = new BranchEntity();
        existingBranch.setId(1L);
        existingBranch.setName("Old Name");

        BranchEntity updatedBranch = new BranchEntity();
        updatedBranch.setId(1L);
        updatedBranch.setName("New Name");

        when(branchRepo.findById(1L)).thenReturn(Mono.just(existingBranch));
        when(branchRepo.save(existingBranch)).thenReturn(Mono.just(updatedBranch));

        Mono<?> result = branchService.updateBranchName(1L, "New Name");

        StepVerifier.create(result)
            .expectNextMatches(b -> ((BranchEntity) b).getName().equals("New Name"))
            .verifyComplete();

        verify(branchRepo, times(1)).findById(1L);
        verify(branchRepo, times(1)).save(existingBranch);
    }

    @Test
    void testAddBranchNoProducts() {
        Branch branch = new Branch();
        branch.setName("Empty Branch");

        BranchEntity savedBranchEntity = BranchMapper.toEntity(branch);
        savedBranchEntity.setId(2L);

        when(branchRepo.save(any(BranchEntity.class))).thenReturn(Mono.just(savedBranchEntity));
        when(productRepo.findByBranchId(savedBranchEntity.getId())).thenReturn(Flux.empty());

        Mono<Branch> result = branchService.addBranch(200L, branch);

        StepVerifier.create(result)
            .expectNextMatches(b -> 
                b.getName().equals("Empty Branch") &&
                b.getProducts().isEmpty()
            )
            .verifyComplete();
    }
}
