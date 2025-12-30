package com.finalmodule.mapper;

import com.finalmodule.dto.request.ProductRequest;
import com.finalmodule.dto.response.ProductResponse;
import com.finalmodule.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id",   target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequest request);
}
