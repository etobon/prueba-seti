package com.example.seti.infrastructure.mapper;

import com.example.seti.domain.Franchise;
import com.example.seti.infrastructure.entity.FranchiseEntity;

public class FranchiseMapper {

	public static FranchiseEntity toEntity(Franchise f) {
        FranchiseEntity e = new FranchiseEntity();
        e.setId(f.getId()); e.setName(f.getName());
        return e;
    }
    public static Franchise toDomain(FranchiseEntity e) {
        Franchise f = new Franchise();
        f.setId(e.getId()); f.setName(e.getName());
        return f;
    }
    
}
