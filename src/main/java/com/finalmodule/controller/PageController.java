package com.finalmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/products")
    public String productsPage() {
        return "product/index";
    }

    @GetMapping("/products/create")
    public String createPage() {
        return "product/create";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductPage() {
        return "product/edit";
    }
}
