package com.example.multipledatasources.service.impl;

import com.example.multipledatasources.entities.posgrerepo.Product;
import com.example.multipledatasources.repository.dbpostgre.ProductRepository;
import com.example.multipledatasources.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public int delete(Product product) {
        repository.delete(product);
        return 1;
    }

    @Override
    public int deleteAll(List<Product> list) {

        repository.deleteAll(list);
        return list.size();
    }
}
