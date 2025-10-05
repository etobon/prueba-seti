package com.example.seti.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.seti.domain.Product;
import com.example.seti.infrastructure.entity.ProductEntity;
import com.example.seti.infrastructure.mapper.ProductMapper;
import com.example.seti.infrastructure.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ProductServiceTest {

	@Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setName("Product 1");

        ProductEntity savedEntity = ProductMapper.toEntity(product);
        savedEntity.setId(1L);

        when(productRepo.save(any(ProductEntity.class))).thenReturn(Mono.just(savedEntity));

        Mono<Product> result = productService.addProduct(100L, product);

//        StepVerifier.create(result)
//            .expectNextMatches(p -> p.getName().equals("Product 1") && p.getBranchId().equals(100L))
//            .verifyComplete();

        verify(productRepo, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testDeleteProduct() {
        when(productRepo.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1L))
            .verifyComplete();

        verify(productRepo, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateStock() {
        ProductEntity existing = new ProductEntity();
        existing.setId(1L);
        existing.setStock(5);

        ProductEntity updated = new ProductEntity();
        updated.setId(1L);
        updated.setStock(10);

        when(productRepo.findById(1L)).thenReturn(Mono.just(existing));
        when(productRepo.save(existing)).thenReturn(Mono.just(updated));

        Mono<?> result = productService.updateStock(1L, 10);

        StepVerifier.create(result)
            .expectNextMatches(p -> ((ProductEntity)p).getStock() == 10)
            .verifyComplete();

        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, times(1)).save(existing);
    }

    @Test
    void testMaxStock() {
        ProductEntity p1 = new ProductEntity();
        p1.setId(1L); p1.setStock(5);
        ProductEntity p2 = new ProductEntity();
        p2.setId(2L); p2.setStock(10);
        ProductEntity p3 = new ProductEntity();
        p3.setId(3L); p3.setStock(8);

        when(productRepo.findByBranchId(100L)).thenReturn(Flux.just(p1,p2,p3));

        Mono<Product> result = productService.maxStock(100L);

        StepVerifier.create(result)
            .expectNextMatches(p -> p.getId().equals(2L) && p.getStock() == 10)
            .verifyComplete();

        verify(productRepo, times(1)).findByBranchId(100L);
    }

    @Test
    void testUpdateProductName() {
        ProductEntity existing = new ProductEntity();
        existing.setId(1L);
        existing.setName("OldName");

        ProductEntity updated = new ProductEntity();
        updated.setId(1L);
        updated.setName("NewName");

        when(productRepo.findById(1L)).thenReturn(Mono.just(existing));
        when(productRepo.save(existing)).thenReturn(Mono.just(updated));

        Mono<?> result = productService.updateProductName(1L, "NewName");

        StepVerifier.create(result)
            .expectNextMatches(p -> ((ProductEntity)p).getName().equals("NewName"))
            .verifyComplete();

        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, times(1)).save(existing);
    }

}
