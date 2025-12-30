package com.finalmodule.entity;

import com.finalmodule.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

