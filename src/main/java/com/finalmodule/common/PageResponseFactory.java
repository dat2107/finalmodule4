package com.finalmodule.common;

import org.springframework.data.domain.Page;

import java.util.function.Function;

public class PageResponseFactory {
    public static <T> PageResponse<T> from(Page<T> page) {
        return PageResponse.<T>builder()
                .items(page.getContent())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .size(page.getSize())
                .build();
    }

    public static <T, R> PageResponse<R> from(Page<T> page, Function<T, R> mapper) {
        return PageResponse.<R>builder()
                .items(
                        page.getContent()
                                .stream()
                                .map(mapper)
                                .toList()
                )
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getNumber())
                .size(page.getSize())
                .build();
    }
}
