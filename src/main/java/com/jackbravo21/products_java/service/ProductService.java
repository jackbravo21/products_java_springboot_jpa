package com.jackbravo21.products_java.service;


import com.jackbravo21.products_java.dto.ProductDTO;
import com.jackbravo21.products_java.exception.CustomException;
import com.jackbravo21.products_java.model.ProductModel;
import com.jackbravo21.products_java.reporitory.ProductRepository;
import com.jackbravo21.products_java.utils.DateUtil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}
		
    public ProductModel serviceProductCreate(ProductDTO productDTO) {    
    	
    	ProductModel product = new ProductModel();
    	product.setName(productDTO.getName());
    	product.setWay(productDTO.getWay());
    	product.setPrice(productDTO.getPrice());
    	product.setDescription(productDTO.getDescription());
    	product.setType(productDTO.getType());
    	product.setCreated_at(DateUtil.getCurrentDateTime());
    	
    	try {
            return productRepository.save(product);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
	        throw new CustomException(500, "Erro ao cadastrar produto!");  
        }
    }
    
    public ProductModel serviceProductEdit(ProductDTO productDTO) {
        try {
        	Long id = productDTO.getId();
            ProductModel product = productRepository.findById(id).orElse(null);
            
            if (product != null) {
                product.setName(productDTO.getName());
                product.setWay(productDTO.getWay());
                product.setPrice(productDTO.getPrice());
                product.setDescription(productDTO.getDescription());
                product.setType(productDTO.getType());
                
                return productRepository.save(product);
            } else {
                System.out.println("Produto não encontrado.");
    	        throw new CustomException(500, "Produto não encontrado!");  
            }
        } catch (Exception e) {
            System.out.println("Erro ao editar produto: " + e.getMessage());
            throw new CustomException(500, "Erro ao editar produto!");
        }
    }
	
    public boolean serviceProductDelete(Long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
            throw new CustomException(500, "Erro ao deletar produto!");
        }
    }
    
    public Optional<ProductModel> serviceGetProduct(Long id) {
        try {
			if (!productRepository.existsById(id)) {
	            throw new CustomException(404, "Erro ao buscar produto: Produto não encontrado!");
	        }
            return productRepository.findById(id);
        } 
        catch(Exception e){
            System.out.println("Erro ao buscar produto: " + e.getMessage());
            throw new CustomException(500, "Erro ao buscar produto!");
        }
    }

    public List<ProductModel> serviceSelectAll(String type) {
        try {
            return productRepository.findByType(type);
        } catch (Exception e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
            throw new CustomException(500, "Erro ao buscar produto!");
        }
    }
}
