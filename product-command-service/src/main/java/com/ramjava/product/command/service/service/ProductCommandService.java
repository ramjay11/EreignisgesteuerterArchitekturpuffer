package com.ramjava.product.command.service.service;

import com.ramjava.product.command.service.entity.Product;
import com.ramjava.product.command.service.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(long id, Product product) {
        Product existingProduct = productRepo.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        return productRepo.save(existingProduct);
    }
}
