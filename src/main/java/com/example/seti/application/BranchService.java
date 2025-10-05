package com.example.seti.application;

import org.springframework.stereotype.Service;

import com.example.seti.domain.Branch;
import com.example.seti.infrastructure.mapper.BranchMapper;
import com.example.seti.infrastructure.mapper.ProductMapper;
import com.example.seti.infrastructure.repository.BranchRepository;
import com.example.seti.infrastructure.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class BranchService {

	private final BranchRepository branchRepo;
    private final ProductRepository productRepo;
    
    public BranchService(BranchRepository branchRepo, ProductRepository productRepo) {
        this.branchRepo = branchRepo; this.productRepo = productRepo;
    }

    public Mono<Branch> addBranch(Long franchiseId, Branch branch) {
    	
        branch.setFranchiseId(franchiseId);
        
        return branchRepo.save(BranchMapper.toEntity(branch)) // Mono<BranchEntity>
            .flatMap(savedBranch -> 
                productRepo.findByBranchId(savedBranch.getId()) // Flux<ProductEntity>
                    .map(ProductMapper::toDomain) // Flux<Product>
                    .collectList() // Mono<List<Product>>
                    .map(products -> BranchMapper.toDomain(savedBranch, products)) // Mono<Branch>
            );
    }
	
    // Plus 
    
    public Mono<?> updateBranchName(Long id, String newName) {
		
		return branchRepo.findById(id)      // busca el branch
                .flatMap(branchDB -> {
                	branchDB.setName(newName);       // actualiza el nombre
                    return branchRepo.save(branchDB); // guarda el producto
                });
       
    }
    
    
}
