package com.backend.server.repository;

import com.backend.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // CRUD işlemleri için gerekli metodlar zaten burada mevcut
}
