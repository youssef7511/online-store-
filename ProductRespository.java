package com.fsb.onlinestore.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsb.onlinestore.dao.entities.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product,Long> {
    
}