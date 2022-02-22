package com.example.multipledatasources.repository.dbpostgre;

import com.example.multipledatasources.entities.posgrerepo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
