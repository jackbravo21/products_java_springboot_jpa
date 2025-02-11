package com.jackbravo21.products_java.reporitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackbravo21.products_java.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

	List<ProductModel> findByType(String type);
}
