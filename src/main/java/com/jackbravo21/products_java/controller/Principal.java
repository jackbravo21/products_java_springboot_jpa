package com.jackbravo21.products_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Principal {

	@GetMapping
	public String principalResponse() {
		return "Seja bem vindo!";
	}
	
}
