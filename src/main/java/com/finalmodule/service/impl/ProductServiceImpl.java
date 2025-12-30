package com.finalmodule.service.impl;

import com.finalmodule.dto.request.ProductRequest;
import com.finalmodule.dto.request.ProductSearchRequest;
import com.finalmodule.entity.Category;
import com.finalmodule.entity.Product;
import com.finalmodule.enums.ProductStatus;
import com.finalmodule.exception.AppException;
import com.finalmodule.exception.ErrorCode;
import com.finalmodule.mapper.ProductMapper;
import com.finalmodule.repository.CategoryRepository;
import com.finalmodule.repository.ProductRepository;
import com.finalmodule.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> search(ProductSearchRequest request, Pageable pageable) {
        return productRepository.search(
                StringUtils.hasText(request.getName()) ? request.getName() : null,
                request.getCategoryId(),
                request.getPrice(),
                pageable
        );
    }

    @Override
    public Product create(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = productMapper.toEntity(request);
        product.setCategory(category);
        product.setStatus(ProductStatus.PENDING);

        return productRepository.save(product);
    }

    @Override
    public List<String> delete(List<Long> ids) {

        List<Product> products = productRepository.findAllById(ids);

        if (products.isEmpty()) {
            return List.of();
        }

        List<String> names = products.stream()
                .map(Product::getName)
                .toList();

        productRepository.deleteAll(products);

        return names;
    }

    @Override
    public void updateStatus(Long productId, ProductStatus status) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        product.setStatus(status);
        productRepository.save(product);
    }


}
