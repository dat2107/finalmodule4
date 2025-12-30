package com.finalmodule.repository;

import com.finalmodule.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT p FROM Product p
        WHERE (:name IS NULL OR p.name LIKE %:name%)
          AND (:categoryId IS NULL OR p.category.id = :categoryId)
          AND (:price IS NULL OR p.price >= :price)
    """)
    Page<Product> search(
            String name,
            Long categoryId,
            Double price,
            Pageable pageable
    );
}

