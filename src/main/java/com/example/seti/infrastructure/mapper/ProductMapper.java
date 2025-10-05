package com.example.seti.infrastructure.mapper;

import com.example.seti.domain.Product;
import com.example.seti.infrastructure.entity.ProductEntity;

public class ProductMapper {
	
	public static ProductEntity toEntity(Product p) {
        ProductEntity e = new ProductEntity();
        e.setId(p.getId()); e.setName(p.getName()); e.setStock(p.getStock()); e.setBranchId(p.getBranchId());
        return e;
    }
    public static Product toDomain(ProductEntity e) {
        Product p = new Product();
        p.setId(e.getId()); p.setName(e.getName()); p.setStock(e.getStock()); p.setBranchId(e.getBranchId());
        return p;
    }
    
}
