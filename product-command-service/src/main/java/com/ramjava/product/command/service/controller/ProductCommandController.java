package com.ramjava.product.command.service.controller;

import com.ramjava.product.command.service.entity.Product;
import com.ramjava.product.command.service.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    @Autowired
    private ProductCommandService commandService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return commandService.createProduct(product);
    }

    @PutMapping("/id")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        return commandService.updateProduct(id, product);
    }
}
