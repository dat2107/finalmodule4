package com.finalmodule.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String status;
    private Long categoryId;
    private String categoryName;
}
