package com.example.seti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.seti.application.BranchService;
import com.example.seti.domain.Branch;
import com.example.seti.domain.Product;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

	@Autowired
	private BranchService service;
	
	@PostMapping("/{franchiseId}") 
    public Mono<Branch> add(@PathVariable Long franchiseId,@RequestBody Branch b){
        return service.addBranch(franchiseId,b);
    }
	
	
	// plus
	
	@PutMapping("/{id}")
	public Mono<Branch> updateName(@PathVariable Long id, @RequestParam String name) {
		return (Mono<Branch>) service.updateBranchName(id, name);
	}
	
}
