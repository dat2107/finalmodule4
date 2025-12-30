package com.finalmodule.dto.request;

import com.finalmodule.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStatusRequest {

    @NotNull
    private ProductStatus status;
}
