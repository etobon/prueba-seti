package com.example.seti.application;

import org.springframework.stereotype.Service;

import com.example.seti.domain.Product;
import com.example.seti.infrastructure.mapper.ProductMapper;
import com.example.seti.infrastructure.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private final ProductRepository productRepo;

	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}


	public Mono<Product> addProduct(Long branchId,Product p) {
		p.setBranchId(branchId);
        return productRepo.save(ProductMapper.toEntity(p)).map(ProductMapper::toDomain)
                   .doOnNext(pr -> System.out.println("Product added: " + pr.getName()));
    }
	
	
	public Mono<Void> deleteProduct(Long productId) {
        return productRepo.deleteById(productId);
    }

	
	public Mono<?> updateStock(Long productId, int newStock) {
		
		return productRepo.findById(productId)      // busca el producto
                .flatMap(product -> {
                    product.setStock(newStock);           // actualiza el stock
                    return productRepo.save(product); // guarda el producto
                });
       
    }
	
	public Mono<Product> maxStock(Long branchId) {
        return productRepo.findByBranchId(branchId)
                   .sort((p1,p2) -> Integer.compare(p2.getStock(), p1.getStock()))
                   .next()
                   .map(ProductMapper::toDomain);
    }
	
	// plus
	
	public Mono<?> updateProductName(Long productId, String newName) {
		
		return productRepo.findById(productId)      // busca el producto
                .flatMap(product -> {
                    product.setName(newName);         // actualiza productName
                    return productRepo.save(product); // guarda el producto
                });
       
    }
}
