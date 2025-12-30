package com.finalmodule.service;

import com.finalmodule.dto.request.ProductRequest;
import com.finalmodule.dto.request.ProductSearchRequest;
import com.finalmodule.entity.Product;
import com.finalmodule.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> search(ProductSearchRequest request, Pageable pageable);

    Product create(ProductRequest request);

    List<String> delete(List<Long> ids);

    void updateStatus(Long productId, ProductStatus status);

    Product getById(Long id);
}
