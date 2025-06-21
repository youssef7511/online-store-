package com.fsb.onlinestore.business.servicesImpl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fsb.onlinestore.business.services.ProductService;
import com.fsb.onlinestore.dao.entities.Product;
import com.fsb.onlinestore.dao.repositories.ProductRespository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRespository productRespository;

    public ProductServiceImpl(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    @Override
    public Product getProduct(Long id) {
        return this.productRespository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product) throws DataIntegrityViolationException {
        return this.productRespository.save(product);
    }

    @Override
    public Product updatePorduct(Long id, Product product) throws DataIntegrityViolationException {
        if (this.productRespository.existsById(id)) {
            return this.productRespository.save(product);
        } else
            return null;
    }

    @Override
    public void deleteProduct(Long id) {
        if (this.productRespository.existsById(id)) {
            this.productRespository.deleteById(id);
        }
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRespository.findAll();
    }

}