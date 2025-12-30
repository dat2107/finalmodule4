package com.finalmodule.controller;

import com.finalmodule.common.BaseResponse;
import com.finalmodule.common.BaseResponseFactory;
import com.finalmodule.common.PageResponse;
import com.finalmodule.common.PageResponseFactory;
import com.finalmodule.dto.request.ProductRequest;
import com.finalmodule.dto.request.ProductSearchRequest;
import com.finalmodule.dto.request.ProductStatusRequest;
import com.finalmodule.dto.response.ProductResponse;
import com.finalmodule.entity.Product;
import com.finalmodule.mapper.ProductMapper;
import com.finalmodule.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final BaseResponseFactory baseResponseFactory;

    @GetMapping
    public BaseResponse<PageResponse<ProductResponse>> search(
            ProductSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Product> result = productService.search(request, PageRequest.of(page, size));

        PageResponse<ProductResponse> pageResponse =  PageResponseFactory.from(result, productMapper::toResponse);

        return baseResponseFactory.success(pageResponse);
    }

    @PostMapping
    public BaseResponse<ProductResponse> create(@RequestBody ProductRequest request) {
        Product product = productService.create(request);
        return baseResponseFactory.success(
                productMapper.toResponse(product)
        );
    }

    @DeleteMapping
    public BaseResponse<?> delete(@RequestBody List<Long> ids
    ) {
        productService.delete(ids);
        return baseResponseFactory.success("success.product.deleted");
    }

    @PatchMapping("/{id}/status")
    public BaseResponse<?> updateStatus(@PathVariable Long id, @RequestBody ProductStatusRequest request) {
        productService.updateStatus(id, request.getStatus());
        return baseResponseFactory.success("success.product.status.updated");
    }

    @GetMapping("/{id}")
    public BaseResponse<ProductResponse> getById(@PathVariable Long id) {
        return baseResponseFactory.success(
                productMapper.toResponse(
                        productService.getById(id)
                )
        );
    }

}
