package com.example.seti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seti.application.FranchiseService;
import com.example.seti.domain.Franchise;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

	@Autowired
	private FranchiseService service;

	@PostMapping
	public Mono<Franchise> add(@RequestBody Franchise f) {
		return service.addFranchise(f);
	}

	@GetMapping
	public Flux<Franchise> getAll() {
		return service.getAll();
	}
	
}
