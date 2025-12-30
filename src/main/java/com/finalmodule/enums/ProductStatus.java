package com.finalmodule.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {

    PENDING("product.status.pending"),
    APPROVED("product.status.approved"),
    SOLD("product.status.sold");

    private final String messageKey;

    ProductStatus(String messageKey) {
        this.messageKey = messageKey;
    }
}
