package com.example.seti.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("branches")
public class BranchEntity {

	@Id
	private Long id;
	private String name;
	private Long franchiseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
