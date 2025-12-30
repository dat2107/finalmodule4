package com.finalmodule.controller;

import com.finalmodule.common.BaseResponse;
import com.finalmodule.common.BaseResponseFactory;
import com.finalmodule.dto.response.CategoryResponse;
import com.finalmodule.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BaseResponseFactory baseResponseFactory;

    @GetMapping
    public BaseResponse<List<CategoryResponse>> getAll() {
        return baseResponseFactory.success(
                categoryService.getAll()
        );
    }
}
