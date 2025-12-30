package com.finalmodule.dto.request;

import com.finalmodule.common.BaseRequest;
import lombok.Data;

@Data
public class ProductRequest extends BaseRequest {
    private String name;
    private Long categoryId;
    private Double price;
}
