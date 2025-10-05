package com.example.seti.infrastructure.mapper;

import java.util.List;

import com.example.seti.domain.Branch;
import com.example.seti.domain.Product;
import com.example.seti.infrastructure.entity.BranchEntity;

public class BranchMapper {

	public static BranchEntity toEntity(Branch b) {
        BranchEntity e = new BranchEntity();
        e.setId(b.getId()); e.setName(b.getName()); e.setFranchiseId(b.getFranchiseId());
        return e;
    }
    public static Branch toDomain(BranchEntity e, List<Product> products) {
        Branch b = new Branch();
        b.setId(e.getId()); b.setName(e.getName()); b.setFranchiseId(e.getFranchiseId());
        b.setProducts(products);
        return b;
    }
    
}
