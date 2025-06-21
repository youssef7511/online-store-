package com.fsb.onlinestore.business.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.fsb.onlinestore.dao.entities.Product;

public interface ProductService {
         // Retrieves a product by ID.
         public Product getProduct(Long id);

         // Retrieves a product by Code.
         //public Product getProductByCode(String id);
     
         // Adds a new product.
         public Product addProduct(Product product) throws DataIntegrityViolationException ;
     
         // Updates an existing product.
         public Product updatePorduct(Long id,Product product) throws DataIntegrityViolationException ;
     
         // Deletes a product by their ID.
         public void deleteProduct(Long id);
     
         // Retrieves all products.
         public List<Product> getAllProduct();
     
}