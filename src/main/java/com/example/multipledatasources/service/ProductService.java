package com.example.multipledatasources.service;

import com.example.multipledatasources.entities.posgrerepo.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    Product save( Product t);

    Product update(Product t);

    int delete(Product t);

    int deleteAll(List<Product> list);
}
