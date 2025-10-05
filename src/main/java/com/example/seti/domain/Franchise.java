package com.example.seti.domain;

import java.util.ArrayList;
import java.util.List;

public class Franchise {

	private Long id;
	private String name;
	private List<Branch> branches = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	
	

}
