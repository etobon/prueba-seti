package com.example.seti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.seti.application.ProductService;
import com.example.seti.domain.Product;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/{branchId}") 
	public Mono<Product> add(@PathVariable Long branchId, @RequestBody Product p) {
		return service.addProduct(branchId, p);
	}

	
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable Long id) {
		return service.deleteProduct(id);
	}

	@PutMapping("/{id}/{stock}")
	public Mono<Product> updateStock(@PathVariable Long id, @PathVariable int stock) {
		return (Mono<Product>) service.updateStock(id, stock);
	}

	@GetMapping("/max/{branchId}")
	public Mono<Product> maxStock(@PathVariable Long branchId) {
		return service.maxStock(branchId);
	}
	
	// plus
	
	@PutMapping("/{id}")
	public Mono<Product> updateName(@PathVariable Long id, @RequestParam String name) {
		return (Mono<Product>) service.updateProductName(id, name);
	}
	

}
