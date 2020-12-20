package com.solotenkov.services;

import com.solotenkov.dao.ProductRepository;
import com.solotenkov.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Map<Long, Product> findAll() {
        return repository.findAll().stream().collect(Collectors.toMap(Product::getId, item -> item));
    }

    @Override
    public Product update(Long id, Product updateProduct) {
        Product product = repository.findById(id).get();
        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        return repository.save(product);
    }

    @Override
    public Map<Long, Product> findByName(String name) {
        return repository.findByName(name).stream().collect(Collectors.toMap(Product::getId, item -> item));
    }
}
