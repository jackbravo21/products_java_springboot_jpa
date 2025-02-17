package com.jackbravo21.products_java.controller;

import com.jackbravo21.products_java.dto.ProductDTO;
import com.jackbravo21.products_java.model.ProductModel;
import com.jackbravo21.products_java.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService)
	{
		this.productService = productService; 
	}
	
	@PostMapping("/create")
	public ResponseEntity<ProductModel> createProduct(@RequestBody ProductDTO productDTO)
	{
		ProductModel saveProduct = productService.serviceProductCreate(productDTO);
		return ResponseEntity.ok(saveProduct);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<ProductModel> editProduct(@RequestBody ProductDTO productDTO) {
		ProductModel updatedProduct = productService.serviceProductEdit(productDTO);
        	return ResponseEntity.ok(updatedProduct);
    	}

    	@DeleteMapping("/delete/{id}")
    	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        	boolean deleted = productService.serviceProductDelete(id);
        	return deleted ? ResponseEntity.ok("Produto deletado com sucesso!") :
                         ResponseEntity.status(404).body("Produto não encontrado!");
    	}

   	@GetMapping("/one/{id}")
    	public ResponseEntity<ProductModel> getOneProduct(@PathVariable Long id) {
        	Optional<ProductModel> product = productService.serviceGetProduct(id);
        	return product.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(404).build());
    	}

    	@GetMapping("/all/{type}")
    	public ResponseEntity<List<ProductModel>> getAllProducts(@PathVariable String type) {
        	List<ProductModel> products = productService.serviceSelectAll(type);
        	return ResponseEntity.ok(products);
    	}

}

