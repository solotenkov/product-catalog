package com.solotenkov.services;
import com.solotenkov.entity.Product;
import java.util.Map;

public interface ProductService {

    Product save(Product product);
    void delete(long id);
    Map<Long, Product> findAll();
    Product update(Long id, Product updateProduct);
    Map<Long, Product> findByName(String name);
}
