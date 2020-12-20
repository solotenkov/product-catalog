package com.solotenkov.controllers;

import com.solotenkov.entity.Product;
import com.solotenkov.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:63342")
@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }


    @PutMapping(path = "/{id}")
    public Product update(@PathVariable (value = "id") long productId,
                          @RequestBody Product productDetails) {
        return productService.update(productId, productDetails);
    }

    @GetMapping(path = "/all")
    public Map<Long, Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/{name}")
    public Map<Long, Product> findByName(@PathVariable (value = "name") String name) {
        if (name == null) return null;
        return productService.findByName(name);
    }


}
